 
/*
Sergio Mokshin
Automação Livre
Fev/2015

http://jeelabs.net/pub/docs/ethercard/index.html

*/
 
#include <EtherCard.h>
#include <EEPROM.h>

#include "Wire.h"



#define PIN_RED 6  
#define PIN_GREEN 5
#define PIN_BLUE 3
#define PIN_ALARM 8


#define DS1307_I2C_ADDRESS 0x68


int MemSaveSaida1 = 1;
int MemSaveSaida2 = 2;
int MemSaveSaida3 = 3;
int MemSaveSaida4 = 4;
int MemSaveRed    = 5;
int MemSaveBlue   = 6;
int MemSaveGreen  = 7;

int ValueSaveSaida1 = 0;
int ValueSaveSaida2 = 0;
int ValueSaveSaida3 = 0;
int ValueSaveSaida4 = 0;
int ValueSaveRed    = 0;
int ValueSaveBlue   = 0;
int ValueSaveGreen  = 0;



// ethernet interface ip address
static byte myip[] = { 192, 168, 1, 200 };
// gateway ip address
static byte gwip[] = { 192, 168, 1, 1 };

// ethernet mac address - must be unique on your network
static byte mymac[] = { 0x74,0x69,0x69,0x2D,0x30,0x31 };
byte Ethernet::buffer[550]; // tcp/ip send and receive buffer
BufferFiller bfill;

byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;

void setup(){  
  
   Wire.begin();
   
     
  //Setup Inicial / descomentar build / comentar
  //EEPROM.write(MemSaveSaida1, 0);  
  //EEPROM.write(MemSaveSaida2, 0);
  //EEPROM.write(MemSaveSaida3, 0);          
  //EEPROM.write(MemSaveSaida4, 0);  
  
  
  Serial.begin(9600);
  Serial.println("Iniciando Setup");      
  
  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, OUTPUT);
  pinMode(A3, OUTPUT);
  pinMode(PIN_ALARM, OUTPUT);
    
  ValueSaveSaida1 = EEPROM.read(MemSaveSaida1);
  ValueSaveSaida2 = EEPROM.read(MemSaveSaida2);
  ValueSaveSaida3 = EEPROM.read(MemSaveSaida3);
  ValueSaveSaida4 = EEPROM.read(MemSaveSaida4);
  ValueSaveRed = EEPROM.read(MemSaveRed);
  ValueSaveBlue = EEPROM.read(MemSaveBlue);
  ValueSaveGreen = EEPROM.read(MemSaveGreen);
    
  digitalWrite(A0, ValueSaveSaida1);
  digitalWrite(A1, ValueSaveSaida2);
  digitalWrite(A2, ValueSaveSaida3);
  digitalWrite(A3, ValueSaveSaida4);


     
  
   analogWrite(5, ValueSaveRed);  
   analogWrite(6, ValueSaveGreen);  
   analogWrite(3, ValueSaveBlue);     
  
  
  
  if (ether.begin(sizeof Ethernet::buffer, mymac) == 0) 
  {
    Serial.println( "Failed to access Ethernet controller");
  }

  ether.staticSetup(myip, gwip);
  ether.printIp("IP:  ", ether.myip);
  ether.printIp("GW:  ", ether.gwip);  
  ether.printIp("DNS: ", ether.dnsip);    
   
  Serial.println("Finalizando Setup");      
}

void loop(){    
  
  getDateDs1307(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);
    
  WebServer();

}


static word homePage() {
  
  Serial.println("Gerando Home Page");  
 
  int S1 = digitalRead(A0);
  int S2 = digitalRead(A1);
  int S3 = digitalRead(A2);
  int S4 = digitalRead(A3);  
  
  int LedR = analogRead(6);  
  int LedG = analogRead(5);  
  int LedB = analogRead(3);  
  
  int AL = digitalRead(PIN_ALARM);  
            
  bfill = ether.tcpOffset();
  
  bfill.emit_p(PSTR("<html><head>"));
  bfill.emit_p(PSTR("<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css' rel='stylesheet'></link>"));
  bfill.emit_p(PSTR("</head>"));
  bfill.emit_p(PSTR("<body>"));
  bfill.emit_p(PSTR("<div class='jumbotron'>"));
  bfill.emit_p(PSTR("<h2>Interface de comando</h2>"));
  bfill.emit_p(PSTR("<div class='row'>"));
  bfill.emit_p(PSTR("<div class='col-md-6'>"));
  
  //SAIDA 1
  if(S1 == HIGH)
  {
    bfill.emit_p(PSTR("<a class='btn btn-success btn-lg' href='/S1/OFF' type='button'>S1 - ON -> Desligar</button></a>"));        
  }
  else
  {
    bfill.emit_p(PSTR("<a class='btn btn-danger btn-lg' href='/S1/ON' type='button'>S1 - OFF -> Ligar</button></a>"));        
  }        
  bfill.emit_p(PSTR("<br><br>"));
  
   //SAIDA 2
  if(S2 == HIGH)
  {
    bfill.emit_p(PSTR("<a class='btn btn-success btn-lg' href='/S2/OFF' type='button'>S2 - ON -> Desligar</button></a>"));        
  }
  else
  {
    bfill.emit_p(PSTR("<a class='btn btn-danger btn-lg' href='/S2/ON' type='button'>S2 - OFF -> Ligar</button></a>"));        
  }        
  bfill.emit_p(PSTR("<br><br>"));
  
  bfill.emit_p( PSTR("$D"), dayOfMonth);  
  bfill.emit_p(PSTR("-"));
  bfill.emit_p( PSTR("$D"), month);  
  bfill.emit_p(PSTR("-"));
  bfill.emit_p( PSTR("20$D"), year);    
  Serial.println(year);
  
  bfill.emit_p(" ");

  bfill.emit_p( PSTR("$D"), hour);    
  bfill.emit_p(PSTR(":"));
  bfill.emit_p( PSTR("$D"), minute);    
  bfill.emit_p(PSTR(":"));
  bfill.emit_p( PSTR("$D"), second);    


  bfill.emit_p(PSTR("</div>"));
  bfill.emit_p(PSTR("</body>"));
  bfill.emit_p(PSTR("</html>"));
      
// http://jeelabs.net/pub/docs/ethercard/classBufferFiller.html
//  "Status: <a href=\"?REL=$F\">$F</a><b>"), http_OK, temp, RELStatus?PSTR("off"):PSTR("on"), RELStatus?PSTR("ON"):PSTR("OFF"));
      
  return bfill.position();
}
 
 



void getDateDs1307(byte * second,
                   byte * minute,
                   byte * hour,
                   byte * dayOfWeek,
                   byte * dayOfMonth,
                   byte * month,
                   byte * year)
{

  Wire.beginTransmission(DS1307_I2C_ADDRESS);
  Wire.write(0);
  Wire.endTransmission();
  Wire.requestFrom(DS1307_I2C_ADDRESS, 7);

  *second     = bcdToDec(Wire.read() & 0x7f);
  *minute     = bcdToDec(Wire.read());
  *hour       = bcdToDec(Wire.read() & 0x3f);  // Need to change this if 12 hour am/pm
  *dayOfWeek  = bcdToDec(Wire.read());
  *dayOfMonth = bcdToDec(Wire.read());
  *month      = bcdToDec(Wire.read());
  *year       = bcdToDec(Wire.read());

}

byte decToBcd(byte val)
{
  return ( (val / 10 * 16) + (val % 10) );
}

byte bcdToDec(byte val)
{
  return ( (val / 16 * 10) + (val % 16) );
}

void setDateDs1307(byte second,        // 0-59
                   byte minute,        // 0-59
                   byte hour,          // 1-23
                   byte dayOfWeek,     // 1-7
                   byte dayOfMonth,    // 1-28/29/30/31
                   byte month,         // 1-12
                   byte year)          // 0-99
{
  Wire.beginTransmission(DS1307_I2C_ADDRESS);
  Wire.write(0);
  Wire.write(decToBcd(second));    // 0 to bit 7 starts the clock
  Wire.write(decToBcd(minute));
  Wire.write(decToBcd(hour));      // If you want 12 hour am/pm you need to set
  // bit 6 (also need to change readDateDs1307)
  Wire.write(decToBcd(dayOfWeek));
  Wire.write(decToBcd(dayOfMonth));
  Wire.write(decToBcd(month));
  Wire.write(decToBcd(year));
  Wire.endTransmission();
}

void WebServer()
{
 word len = ether.packetReceive();
  word pos = ether.packetLoop(len);
  
 // char* dados =(char *)Ethernet::buffer + pos;  
 // if(pos >0)
 // {
 //    Serial.println(dados);  
 // }
  
    if(strstr((char *)Ethernet::buffer + pos, "GET /S1/ON") != 0) {
      Serial.println("Received ON command");
      digitalWrite(A0, HIGH);
      EEPROM.write(MemSaveSaida1, 1);
    }
    if(strstr((char *)Ethernet::buffer + pos, "GET /S1/OFF") != 0) {
      Serial.println("Received OFF command");
      digitalWrite(A0, LOW);
      EEPROM.write(MemSaveSaida1, 0);
    }
    
  if(strstr((char *)Ethernet::buffer + pos, "GET /S2/ON") != 0) {
      Serial.println("Received ON command");
       digitalWrite(A1, HIGH);
       EEPROM.write(MemSaveSaida2, 1);
    }
    if(strstr((char *)Ethernet::buffer + pos, "GET /S2/OFF") != 0) {
      Serial.println("Received OFF command");
       digitalWrite(A1, LOW);
       EEPROM.write(MemSaveSaida2, 0);   
    }

  if(strstr((char *)Ethernet::buffer + pos, "GET /S3/ON") != 0) {
      Serial.println("Received ON command");
       digitalWrite(A2, HIGH);
       EEPROM.write(MemSaveSaida3, 1); 
    }
    if(strstr((char *)Ethernet::buffer + pos, "GET /S3/OFF") != 0) {
      Serial.println("Received OFF command");
       digitalWrite(A2, LOW);
       EEPROM.write(MemSaveSaida3, 0);
       
    }

  if(strstr((char *)Ethernet::buffer + pos, "GET /S4/ON") != 0) {
      Serial.println("Received ON command");
       digitalWrite(A3, HIGH);
       EEPROM.write(MemSaveSaida4, 1);       
    }
  if(strstr((char *)Ethernet::buffer + pos, "GET /S4/OFF") != 0) {
      Serial.println("Received OFF command");
      digitalWrite(A3, LOW);
      EEPROM.write(MemSaveSaida4, 0);
   }     
   
  if(strstr((char *)Ethernet::buffer + pos, "GET /R/ON") != 0) {
      Serial.println("Received OFF command");
      analogWrite(5, 255);  
      EEPROM.write(MemSaveRed, 255);
      ValueSaveRed = 255;
   }
   
   if(strstr((char *)Ethernet::buffer + pos, "GET /R/OFF") != 0) {
      Serial.println("Received OFF command");
      analogWrite(5, 0);  
      EEPROM.write(MemSaveRed, 0);
      ValueSaveRed = 0;
   }
   
   if(strstr((char *)Ethernet::buffer + pos, "GET /G/ON") != 0) {
      Serial.println("Received OFF command");
      analogWrite(6, 255);  
      EEPROM.write(MemSaveGreen, 255);      
      ValueSaveGreen = 255;
   }
   
   if(strstr((char *)Ethernet::buffer + pos, "GET /G/OFF") != 0) {
      Serial.println("Received OFF command");
      analogWrite(6, 0);  
      EEPROM.write(MemSaveGreen, 0);            
      ValueSaveGreen = 0;
   }
   
   if(strstr((char *)Ethernet::buffer + pos, "GET /B/ON") != 0) {
      Serial.println("Received OFF command");
      analogWrite(3, 255);  
      EEPROM.write(MemSaveBlue, 255);       
      ValueSaveBlue = 255;     
   }
   
   if(strstr((char *)Ethernet::buffer + pos, "GET /B/OFF") != 0) {
      Serial.println("Received OFF command");
      analogWrite(3, 0);  
      EEPROM.write(MemSaveBlue, 0);                        
      ValueSaveBlue = 0;      
   }
   
   
   if (pos) 
   {
    ether.httpServerReply(homePage());   
   }   
}



