/* ====== ESP8266 Demo ======
 * Print out analog values
 * (Updated Dec 14, 2014)
 * ==========================
 *
 * Change SSID and PASS to match your WiFi settings.
 * The IP address is displayed to soft serial upon successful connection.
 *
 * Ray Wang @ Rayshobby LLC
 * http://rayshobby.net/?p=9734
 */

// comment this part out if not using LCD debug

#define BUFFER_SIZE 512

#define SSID  "SERGIO"      // change this to match your WiFi SSID
#define PASS  "992581315"  // change this to match your WiFi password
#define PORT  "8080"           // using port 8080 by default

char buffer[BUFFER_SIZE];

#include <SoftwareSerial.h>
SoftwareSerial esp(12,11);  // use pins 7, 8 for software serial 
#define dbg Serial


// If your MCU has dual USARTs (e.g. ATmega644)
// Use the definitions below
//#define dbg Serial    // use Serial for debug
//#define esp Serial1   // use Serial1 to talk to esp8266

// By default we are looking for OK\r\n
char OKrn[] = "OK\r\n";
byte wait_for_esp_response(int timeout, char* term=OKrn) {
  unsigned long t=millis();
  bool found=false;
  int i=0;
  int len=strlen(term);
  // wait for at most timeout milliseconds
  // or if OK\r\n is found
  while(millis()<t+timeout) {
    if(esp.available()) {
      buffer[i++]=esp.read();
      if(i>=len) {
        if(strncmp(buffer+i-len, term, len)==0) {
          found=true;
          break;
        }
      }
    }
  }
  buffer[i]=0;
  dbg.print(buffer);
  return found;
}

void setup() {

  // assume esp8266 operates at 115200 baud rate
  // change if necessary to match your modules' baud rate
  esp.begin(9600);
  
  dbg.begin(9600);
  dbg.println("begin.");
    
  setupWiFi();

  // print device IP address
  dbg.print("device ip addr:");
  esp.println("AT+CIFSR");
  wait_for_esp_response(1000);
}

bool read_till_eol() {
  static int i=0;
  if(esp.available()) {
    buffer[i++]=esp.read();
    if(i==BUFFER_SIZE)  i=0;
    if(i>1 && buffer[i-2]==13 && buffer[i-1]==10) {
      buffer[i]=0;
      i=0;
      dbg.print(buffer);
      return true;
    }
  }
  return false;
}

void loop() {
  int ch_id, packet_len;
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
        if (strncmp(pb, "GET /", 5) == 0) {
          wait_for_esp_response(1000);
          dbg.println("-> serve homepage");
          serve_homepage(ch_id);
        }
      }
    }
  }
}

void serve_homepage(int ch_id) {
  String header = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nConnection: close\r\nRefresh: 5\r\n";

  String content="";
  // output the value of each analog input pin
  for (int analogChannel = 0; analogChannel < 6; analogChannel++) {
    int sensorReading = analogRead(analogChannel);
    content += "analog input ";
    content += analogChannel;
    content += " is ";
    content += sensorReading;
    content += "<br />\n";       
  }

  header += "Content-Length:";
  header += (int)(content.length());
  header += "\r\n\r\n";
  esp.print("AT+CIPSEND=");
  esp.print(ch_id);
  esp.print(",");
  esp.println(header.length()+content.length());
  if(wait_for_esp_response(2000, "> ")) {
    esp.print(header);
    esp.print(content);
  } else {
    esp.print("AT+CIPCLOSE=");
    esp.println(ch_id);
  }
}


void setupWiFi() {
  // try empty AT command
  esp.println("AT");
  wait_for_esp_response(1000);

  // set mode 1 (client)
  esp.println("AT+CWMODE=1");
  wait_for_esp_response(1000);  

  // reset WiFi module
  esp.print("AT+RST\r\n");
  wait_for_esp_response(1500);
  delay(3000);
 
  // join AP
  esp.print("AT+CWJAP=\"");
  esp.print(SSID);
  esp.print("\",\"");
  esp.print(PASS);
  esp.println("\"");
  // this may take a while, so wait for 5 seconds
  wait_for_esp_response(5000);
  
  esp.println("AT+CIPSTO=30");  
  wait_for_esp_response(1000);

  // start server
  esp.println("AT+CIPMUX=1");
  wait_for_esp_response(1000);
  
  esp.print("AT+CIPSERVER=1,"); // turn on TCP service
  esp.println(PORT);
  wait_for_esp_response(1000);
  
    
}
