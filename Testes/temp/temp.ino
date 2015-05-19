/* ====== Serial8266 + Arduino Webserver ======
 * 24.12.2014
 * Simple arduino + Serial8266 webserver 
 * using AT commands and GET forms
 * AT from http://www.ai-thinker.com/
 * V0.9.2.2
 * Chirila Cezar @epilepsynerd.wordpress.com
 * Thanks to :
 * Ray Wang @ Rayshobby LLC
 * http://rayshobby.net/?p=9734
 */

#define BUFFER_SIZE 512
#define GET_SIZE 64
//#define SSID  "Abraham Linksys"      
//#define PASS  "mobydick"  
#define PORT  "80"           
#define dbg Serial  //For debugging purposes

char buffer[BUFFER_SIZE];
char get_s[GET_SIZE];
char OKrn[] = "OK\r\n"; 


#include <SoftwareSerial.h>

//Software serial to communicate with the Serial
//SoftwareSerial Serial(11, 12); //RX, TX
//SoftwareSerial Serial(1, 0); //RX, TX

byte wait_for_Serial_rSerialonse(int timeout, char* term=OKrn) {
  unsigned long t=millis();
  bool found=false;
  int i=0;
  int len=strlen(term);
  // wait for at most timeout milliseconds
  // or if OK\r\n is found
  while(millis()<t+timeout) {
    if(Serial.available()) {
      buffer[i++]=Serial.read();
      if(i>=len) {
        if(strncmp(buffer+i-len, term, len)==0) {
          found=true;
          break;
        }
      }
    }
  }
  buffer[i]=0;
  //dbg.print(buffer);
  return found;
}

void setup() {

  Serial.begin(9600); //Serial baud rate
  //dbg.begin(9600); //debugger baud rate

  //dbg.println("begin.");

  setupWiFi(2,"","");

  // print device IP address
  //dbg.print("device ip addr:");
  Serial.println("AT+CIFSR");
  wait_for_Serial_rSerialonse(1000);
}

void loop() {
  int ch_id, packet_len, lssid = 0, lpass = 0;
  char *pb;  
  if(read_till_eol()) {
    if(strncmp(buffer, "+IPD,", 5)==0) {
      // request: +IPD,ch,len:data
      sscanf(buffer+5, "%d,%d", &ch_id, &packet_len);
      if (packet_len > 0) {
        // read serial until packet_len character received
        // start from :
        pb = buffer+5;
        while(*pb!=':') pb++;
        pb++;
        get_data(pb, ch_id);
      }
    }
  }
}

void get_data(char *pb, int ch_id){
  char *pget;
  if (strncmp(pb, "GET /favicon.ico", 16) == 0) {
    pb = pb+6;
    wait_for_Serial_rSerialonse(1000);
  }

  if (strncmp(pb, "GET /?", 6) == 0) {
    pb = pb+6;
    pget = get_s;
    while(*pb!=' '){
      *pget = *pb;
      pb++; 
      pget++;
    }
    sort_data();
    wait_for_Serial_rSerialonse(1000);
    //dbg.println("-> serve homepage\n");
    serve_homepage(ch_id);

  }

  else if (strncmp(pb, "GET /", 5) == 0) {
    wait_for_Serial_rSerialonse(1000);
    //dbg.println("-> serve homepage\n");
    serve_homepage(ch_id);
  }
}

void sort_data(){
  String _ssid ="";
  String _pass ="";

  char *pget;
  pget = get_s;

  pget = pget+5;

  while(*pget!='&'){
    _ssid += *pget;
    pget++; 
  }

  pget = pget+10;
  while(*pget!=' '){
    _pass += *pget;
    pget++; 
  }
  wait_for_Serial_rSerialonse(2000);
  setupWiFi(1, _ssid, _pass);
}

void serve_homepage(int ch_id) {
  String header = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n";

  String content="";
  // output the value of each analog input pin
  content += "<form method=get>";
  content += "<label>SSID</label><br>";
  content += "<input  type='text' name='ssid' maxlength='30' size='15'><br>";
  content += "<label>Password</label><br>";
  content += "<input  type='password' name='password' maxlength='30' size='15'><br><br>";
  content += "<input  type='submit' value='connect' >";
  content += "</form>";

  header += "Content-Length:";
  header += (int)(content.length());
  header += "\r\n\r\n";
  Serial.print("AT+CIPSEND=");
  Serial.print(ch_id);
  Serial.print(",");
  Serial.println(header.length()+content.length());
  if(wait_for_Serial_rSerialonse(2000, "> ")) {
    Serial.print(header);
    Serial.print(content);
  } 
  else {
    Serial.print("AT+CIPCLOSE=");
    Serial.println(ch_id);
  }
}


void setupWiFi(int mode, String SSID, String PASS) {
  // try empty AT command
  Serial.println("AT");
  wait_for_Serial_rSerialonse(1000);

  // set mode 1 (client) or mode 2 (AP)
  if(mode==1){
    Serial.println("AT+CWMODE=1");
    wait_for_Serial_rSerialonse(1000);  
  }
  else if(mode==2){
    Serial.println("AT+CWMODE=2");
    wait_for_Serial_rSerialonse(1000);  
  }

  // reset WiFi module
  Serial.print("AT+RST\r\n");
  wait_for_Serial_rSerialonse(1500);
  wait_for_Serial_rSerialonse(3000);

  if(mode==1){
    // join AP
    Serial.print("AT+CWJAP=\"");
    Serial.print(SSID);
    Serial.print("\",\"");
    Serial.print(PASS);
    Serial.println("\"");
    // this may take a while, so wait for 5 seconds
    wait_for_Serial_rSerialonse(5000);
  }
  // start server
  Serial.println("AT+CIPMUX=1");
  wait_for_Serial_rSerialonse(1000);

  Serial.print("AT+CIPSERVER=1,"); // turn on TCP service
  Serial.println(PORT);
  wait_for_Serial_rSerialonse(1000);

}



bool read_till_eol() {
  static int i=0;
  if(Serial.available()) {
    buffer[i++]=Serial.read();
    if(i==BUFFER_SIZE)  i=0;
    if(i>1 && buffer[i-2]==13 && buffer[i-1]==10) {
      buffer[i]=0;
      i=0;
      //dbg.print(buffer);
      return true;
    }
  }
  return false;
}








