
/*
Sergio Mokshin
Automação Livre
Fev/2015

http://jeelabs.net/pub/docs/ethercard/index.html
// http://jeelabs.net/pub/docs/ethercard/classBufferFiller.html
//  "Status: <a href=\"?REL=$F\">$F</a><b>"), http_OK, temp, RELStatus?PSTR("off"):PSTR("on"), RELStatus?PSTR("ON"):PSTR("OFF"));


*/

#include <EtherCard.h>
#include <EEPROM.h>

#include "Wire.h"

#define PIN_RED 6
#define PIN_GREEN 5
#define PIN_BLUE 3
#define PIN_ALARM 8

#define DS1307_I2C_ADDRESS 0x68

#define PIN_S1 2
#define PIN_S2 4
#define PIN_S3 7
#define PIN_S4 9
#define PIN_S5 A0
#define PIN_S6 A1
#define PIN_S7 A2
#define PIN_S8 A3

#define PIN_RED 3
#define PIN_GREEN 6
#define PIN_BLUE 5

int MemSaida1 = 1; //Endereço de memoria com conteudo ultimo comando enviado S1
int MemSaida2 = 2; //Endereço de memoria com conteudo ultimo comando enviado S2
int MemSaida3 = 3; //Endereço de memoria com conteudo ultimo comando enviado S3
int MemSaida4 = 4; //Endereço de memoria com conteudo ultimo comando enviado S4
int MemSaida5 = 5; //Endereço de memoria com conteudo ultimo comando enviado S5
int MemSaida6 = 6; //Endereço de memoria com conteudo ultimo comando enviado S6
int MemSaida7 = 7; //Endereço de memoria com conteudo ultimo comando enviado S7
int MemSaida8 = 8; //Endereço de memoria com conteudo ultimo comando enviado S8
int MemAuto   = 9; //Endereço de memoria com conteudo ultimo comando enviado modo Automatico/Manual

int MemSaida1HrI = 6; //Endereço de memoria com conteudo inicio horario Saida 1
int MemSaida1HrF = 7; //Endereço de memoria com conteudo fim horario Saida 1
int MemSaida2HrI = 8; //Endereço de memoria com conteudo inicio horario Saida 2
int MemSaida2HrF = 9; //Endereço de memoria com conteudo fim horario Saida 2
int MemSaida3HrI = 10; //Endereço de memoria com conteudo inicio horario Saida 3
int MemSaida3HrF = 11; //Endereço de memoria com conteudo fim horario Saida 3
int MemSaida4HrI = 12; //Endereço de memoria com conteudo inicio horario Saida 4
int MemSaida4HrF = 13; //Endereço de memoria com conteudo fim horario Saida 4
int MemSaida5HrI = 13; //Endereço de memoria com conteudo inicio horario Saida 5
int MemSaida5HrF = 14; //Endereço de memoria com conteudo fim horario Saida 5
int MemSaida6HrI = 15; //Endereço de memoria com conteudo inicio horario Saida 6
int MemSaida6HrF = 16; //Endereço de memoria com conteudo fim horario Saida 6
int MemSaida7HrI = 17; //Endereço de memoria com conteudo inicio horario Saida 7
int MemSaida7HrF = 18; //Endereço de memoria com conteudo fim horario Saida 7
int MemSaida8HrI = 19; //Endereço de memoria com conteudo inicio horario Saida 8
int MemSaida8HrF = 20; //Endereço de memoria com conteudo fim horario Saida 8

int MemRGBHrI = 21; //Endereço de memoria com conteudo inicio horario RGB
int MemRGBHrF = 22; //Endereço de memoria com conteudo fim horario RGB
int MemRGBType = 23; //Endereço de memoria com conteudo inicio horario RGB com cor Azul
int MemRGBBLUEHrF = 24; //Endereço de memoria com conteudo fim horario RGB com cor Azul

int MemRed = 25; //Endereço de memoria com ultimo comando enviado Red
int MemGreen = 26; //Endereço de memoria com ultimo comando enviado Green
int MemBlue = 27; //Endereço de memoria com ultimo comando enviado Blue

int ValueSaveSaida1 = 0; //Conteudo da memoria com status Saida 1
int ValueSaveSaida2 = 0; //Conteudo da memoria com status Saida 2
int ValueSaveSaida3 = 0; //Conteudo da memoria com status Saida 3
int ValueSaveSaida4 = 0; //Conteudo da memoria com status Saida 4
int ValueSaveSaida5 = 0; //Conteudo da memoria com status Saida 5
int ValueSaveSaida6 = 0; //Conteudo da memoria com status Saida 6
int ValueSaveSaida7 = 0; //Conteudo da memoria com status Saida 7
int ValueSaveSaida8 = 0; //Conteudo da memoria com status Saida 8
int ValueSaveAuto   = 0; //Conteudo da memoria com status Automatico ou  Manual

int ValueSaida1HrI = 0; //Conteudo da memoria com inicio horario Saida 1
int ValueSaida1HrF = 0; //Conteudo da memoria fim horario Saida 1
int ValueSaida2HrI = 0; //Conteudo da memoria com inicio horario Saida 2
int ValueSaida2HrF = 0; //Conteudo da memoria fim horario Saida 2
int ValueSaida3HrI = 0; //Conteudo da memoria com inicio horario Saida 3
int ValueSaida3HrF = 0; //Conteudo da memoria fim horario Saida 3
int ValueSaida4HrI = 0; //Conteudo da memoria inicio horario Saida 4
int ValueSaida4HrF = 0; //Conteudo da memoria fim horario Saida 4
int ValueSaida5HrI = 0; //Conteudo da memoria inicio horario Saida 5
int ValueSaida5HrF = 0; //Conteudo da memoria fim horario Saida 5
int ValueSaida6HrI = 0; //Conteudo da memoria inicio horario Saida 6
int ValueSaida6HrF = 0; //Conteudo da memoria fim horario Saida 6
int ValueSaida7HrI = 0; //Conteudo da memoria inicio horario Saida 7
int ValueSaida7HrF = 0; //Conteudo da memoria fim horario Saida 7
int ValueSaida8HrI = 0; //Conteudo da memoria inicio horario Saida 8
int ValueSaida8HrF = 0; //Conteudo da memoria fim horario Saida 8
int ValueRGBHrI = 0; //Conteudo de memoria inicio horario RGB
int ValueRGBHrF = 0; //Conteudo de memoria fim horario RGB
int ValueRGBType = 0; //Conteudo de memoria com cor RGB para agendamento
int ValueRed = 0; //Conteudo de memoria  Red
int ValueGreen = 0; //Conteudo de memoria  Green
int ValueBlue = 0; //Conteudo de memoria  Blue

// ethernet interface ip address
static byte myip[] = { 192, 168, 1, 202 };
// gateway ip address
static byte gwip[] = { 192, 168, 1, 1 };

// ethernet mac address - must be unique on your network
static byte mymac[] = { 0x74, 0x69, 0x69, 0x2D, 0x30, 0x31 };
byte Ethernet::buffer[550]; // tcp/ip send and receive buffer
BufferFiller bfill;


byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;

void setup() {

  Wire.begin();

  Serial.begin(9600);
  Serial.println("Iniciando Setup");

  pinMode(PIN_S1, OUTPUT);
  pinMode(PIN_S2, OUTPUT);
  pinMode(PIN_S3, OUTPUT);
  pinMode(PIN_S4, OUTPUT);
  pinMode(PIN_S5, OUTPUT);
  pinMode(PIN_S6, OUTPUT);
  pinMode(PIN_S7, OUTPUT);
  pinMode(PIN_S8, OUTPUT);

  pinMode(PIN_RED, OUTPUT);
  pinMode(PIN_BLUE, OUTPUT);
  pinMode(PIN_GREEN, OUTPUT);

  ValueSaveAuto = EEPROM.read(MemAuto);

  ValueSaida1HrI = EEPROM.read(MemSaida1HrI);
  ValueSaida1HrF = EEPROM.read(MemSaida1HrF);
  ValueSaida2HrI = EEPROM.read(MemSaida2HrI);
  ValueSaida2HrF = EEPROM.read(MemSaida2HrF);
  ValueSaida3HrI = EEPROM.read(MemSaida3HrI);
  ValueSaida3HrF = EEPROM.read(MemSaida3HrF);
  ValueSaida4HrI = EEPROM.read(MemSaida4HrI);
  ValueSaida4HrF = EEPROM.read(MemSaida4HrF);
  ValueSaida5HrI = EEPROM.read(MemSaida5HrI);
  ValueSaida5HrF = EEPROM.read(MemSaida5HrF);
  ValueSaida6HrI = EEPROM.read(MemSaida6HrI);
  ValueSaida6HrF = EEPROM.read(MemSaida6HrF);
  ValueSaida7HrI = EEPROM.read(MemSaida7HrI);
  ValueSaida7HrF = EEPROM.read(MemSaida7HrF);
  ValueSaida8HrI = EEPROM.read(MemSaida8HrI);
  ValueSaida8HrF = EEPROM.read(MemSaida8HrF);
  ValueRGBHrI = EEPROM.read(MemRGBHrI);
  ValueRGBHrF = EEPROM.read(MemRGBHrF);
  ValueRGBType = EEPROM.read(MemRGBType);
  ValueRed = EEPROM.read(MemRed);
  ValueGreen = EEPROM.read(MemGreen);
  ValueBlue = EEPROM.read(MemBlue);


  //Inicializando placa com valores armazenados na FLASH Memory
  digitalWrite(PIN_S1, EEPROM.read(MemSaida1));
  digitalWrite(PIN_S2, EEPROM.read(MemSaida2));
  digitalWrite(PIN_S3, EEPROM.read(MemSaida3));
  digitalWrite(PIN_S4, EEPROM.read(MemSaida4));
  digitalWrite(PIN_S5, EEPROM.read(MemSaida5));
  digitalWrite(PIN_S6, EEPROM.read(MemSaida6));
  digitalWrite(PIN_S7, EEPROM.read(MemSaida7));
  digitalWrite(PIN_S8, EEPROM.read(MemSaida8));


  analogWrite(PIN_RED, ValueRed);
  analogWrite(PIN_GREEN, ValueGreen);
  analogWrite(PIN_BLUE, ValueBlue);


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

void loop() {

  getDateDs1307(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);
  WebServer();
  ModoAuto();

}


static word homePage() {

  Serial.println("Gerando Home Page");

  ValueSaveSaida1 = digitalRead(PIN_S1);
  ValueSaveSaida2 = digitalRead(PIN_S2);
  ValueSaveSaida3 = digitalRead(PIN_S3);
  ValueSaveSaida4 = digitalRead(PIN_S4);
  ValueSaveSaida5 = digitalRead(PIN_S5);
  ValueSaveSaida6 = digitalRead(PIN_S6);
  ValueSaveSaida7 = digitalRead(PIN_S7);
  ValueSaveSaida8 = digitalRead(PIN_S8);

  ValueRed = analogRead(PIN_RED);
  ValueGreen = analogRead(PIN_GREEN);
  ValueBlue = analogRead(PIN_BLUE);


  bfill = ether.tcpOffset();

  bfill.emit_p(PSTR("HTTP/1.1 200 OK")); //send new page
  bfill.emit_p(PSTR("Content-Type: application/json\r\n\r\n"));
  bfill.emit_p(PSTR("\n"));
  bfill.emit_p(PSTR("dataCB"));
  bfill.emit_p(PSTR("({"));


  bfill.emit_p(PSTR("\"Auto\":\""));
  bfill.emit_p(PSTR("$D"), ValueSaveAuto);
  bfill.emit_p(PSTR("\""));


  bfill.emit_p(PSTR(",\"Day\":"));
  bfill.emit_p(PSTR("$D"), dayOfMonth);
  bfill.emit_p(PSTR(",\"Mounth\":"));
  bfill.emit_p(PSTR("$D"), month);
  bfill.emit_p(PSTR(",\"Year\":"));
  bfill.emit_p(PSTR("$D"), year);
  bfill.emit_p(PSTR(",\"Hour\":"));
  bfill.emit_p(PSTR("$D"), hour);
  bfill.emit_p(PSTR(",\"Minute\":"));
  bfill.emit_p(PSTR("$D"), minute);
  bfill.emit_p(PSTR(",\"Second\":"));
  bfill.emit_p(PSTR("$D"), second);


  bfill.emit_p(PSTR(",\"temp\":0"));
  //  bfill.emit_p(PSTR("$D"), temp);
  bfill.emit_p(PSTR(",\"humidity\":0"));
  //  bfill.emit_p(PSTR("$D"), humidity);



  bfill.emit_p(PSTR(",\"S1\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida1);
  bfill.emit_p(PSTR(",\"S2\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida2);
  bfill.emit_p(PSTR(",\"S3\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida3);
  bfill.emit_p(PSTR(",\"S4\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida4);
  bfill.emit_p(PSTR(",\"S5\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida5);
  bfill.emit_p(PSTR(",\"S6\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida6);
  bfill.emit_p(PSTR(",\"S7\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida7);
  bfill.emit_p(PSTR(",\"S8\":"));
  bfill.emit_p(PSTR("$D"), ValueSaveSaida8);

  bfill.emit_p(PSTR(",\"AgeS1HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida1HrI);
  bfill.emit_p(PSTR(",\"AgeS1HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida1HrF);
  bfill.emit_p(PSTR(",\"AgeS2HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida2HrI);
  bfill.emit_p(PSTR(",\"AgeS2HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida2HrF);
  bfill.emit_p(PSTR(",\"AgeS3HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida3HrI);
  bfill.emit_p(PSTR(",\"AgeS3HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida3HrF);
  bfill.emit_p(PSTR(",\"AgeS4HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida4HrI);
  bfill.emit_p(PSTR(",\"AgeS4HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida4HrF);
  bfill.emit_p(PSTR(",\"AgeS5HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida5HrI);
  bfill.emit_p(PSTR(",\"AgeS5HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida5HrF);
  bfill.emit_p(PSTR(",\"AgeS6HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida6HrI);
  bfill.emit_p(PSTR(",\"AgeS6HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida6HrF);
  bfill.emit_p(PSTR(",\"AgeS7HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida7HrI);
  bfill.emit_p(PSTR(",\"AgeS7HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida7HrF);
  bfill.emit_p(PSTR(",\"AgeS8HrI\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida8HrI);
  bfill.emit_p(PSTR(",\"AgeS8HrF\":"));
  bfill.emit_p(PSTR("$D"), ValueSaida8HrF);

  bfill.emit_p(PSTR(",\"AgeRGBHrI\":"));
  bfill.emit_p(PSTR("$D"), ValueRGBHrI);
  bfill.emit_p(PSTR(",\"AgeRGBHrF\":"));
  bfill.emit_p(PSTR("$D"), ValueRGBHrF);

  bfill.emit_p(PSTR(",\"Red\":"));
  bfill.emit_p(PSTR("$D"), ValueRed);
  bfill.emit_p(PSTR(",\"Green\":"));
  bfill.emit_p(PSTR("$D"), ValueGreen);
  bfill.emit_p(PSTR(",\"Blue\":"));
  bfill.emit_p(PSTR("$D"), ValueBlue);


  bfill.emit_p(PSTR("})"));



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


  if (strstr((char *)Ethernet::buffer + pos, "?AUTOL") != 0) {
    ValueSaveAuto = 1;
    EEPROM.write(MemAuto, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?AUTOD") != 0) {
    ValueSaveAuto = 0;
    EEPROM.write(MemAuto, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S1L") != 0) {
    digitalWrite(PIN_S1, HIGH);
    EEPROM.write(MemSaida1, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S1D") != 0) {
    digitalWrite(PIN_S1, LOW);
    EEPROM.write(MemSaida1, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S2L") != 0) {
    digitalWrite(PIN_S2, HIGH);
    EEPROM.write(MemSaida2, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S2D") != 0) {
    digitalWrite(PIN_S2, LOW);
    EEPROM.write(MemSaida2, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S3L") != 0) {
    digitalWrite(PIN_S3, HIGH);
    EEPROM.write(MemSaida3, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S3D") != 0) {
    digitalWrite(PIN_S3, LOW);
    EEPROM.write(MemSaida3, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S4L") != 0) {
    digitalWrite(PIN_S4, HIGH);
    EEPROM.write(MemSaida4, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S4D") != 0) {
    digitalWrite(PIN_S4, LOW);
    EEPROM.write(MemSaida4, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S5L") != 0) {
    digitalWrite(PIN_S5, HIGH);
    EEPROM.write(MemSaida5, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S5D") != 0) {
    digitalWrite(PIN_S5, LOW);
    EEPROM.write(MemSaida5, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S6L") != 0) {
    digitalWrite(PIN_S6, HIGH);
    EEPROM.write(MemSaida6, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S6D") != 0) {
    digitalWrite(PIN_S6, LOW);
    EEPROM.write(MemSaida6, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S7L") != 0) {
    digitalWrite(PIN_S7, HIGH);
    EEPROM.write(MemSaida7, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S7D") != 0) {
    digitalWrite(PIN_S7, LOW);
    EEPROM.write(MemSaida7, 0);
  }

  if (strstr((char *)Ethernet::buffer + pos, "?S8L") != 0) {
    digitalWrite(PIN_S8, HIGH);
    EEPROM.write(MemSaida8, 1);
  }
  if (strstr((char *)Ethernet::buffer + pos, "?S8D") != 0) {
    digitalWrite(PIN_S8, LOW);
    EEPROM.write(MemSaida8, 0);
  }

   char* data = (char *) Ethernet::buffer + pos;
   String readString2 = "";

   int sz = sizeof(data);
   for(int i = 0;  i < sz ; i++)
      readString2 += data[i];
   
   
//   if (readString2.indexOf("?AgeS1HrI") > 0) {
 if (strstr((char *)Ethernet::buffer + pos, "?AgeS1HrI") != 0) {     

        Serial.println("Inicio Comando");
        Serial.println(readString2);
    
      int cmd = readString2.substring(readString2.indexOf("y") + 1, readString2.lastIndexOf("y")).toInt();
      EEPROM.write(MemSaida1HrI, cmd);
      ValueSaida1HrI = cmd;


       Serial.println("Comando");
        Serial.println(cmd);
 

      cmd = readString2.substring(readString2.indexOf("z") + 1, readString2.lastIndexOf("z")).toInt();
      EEPROM.write(MemSaida1HrF, cmd);
      ValueSaida1HrF = cmd;

    }




  if (strstr((char *)Ethernet::buffer + pos, "GET /R/ON") != 0) {
    analogWrite(5, 255);
    EEPROM.write(MemRed, 255);
    ValueRed = 255;
  }

  if (strstr((char *)Ethernet::buffer + pos, "GET /R/OFF") != 0) {
    analogWrite(5, 0);
    EEPROM.write(MemRed, 0);
    ValueRed = 0;
  }

  if (strstr((char *)Ethernet::buffer + pos, "GET /G/ON") != 0) {
    analogWrite(6, 255);
    EEPROM.write(MemGreen, 255);
    ValueGreen = 255;
  }

  if (strstr((char *)Ethernet::buffer + pos, "GET /G/OFF") != 0) {
    analogWrite(6, 0);
    EEPROM.write(MemGreen, 0);
    ValueGreen = 0;
  }

  if (strstr((char *)Ethernet::buffer + pos, "GET /B/ON") != 0) {
    analogWrite(3, 255);
    EEPROM.write(MemBlue, 255);
    ValueBlue = 255;
  }

  if (strstr((char *)Ethernet::buffer + pos, "GET /B/OFF") != 0) {
    analogWrite(3, 0);
    EEPROM.write(MemBlue, 0);
    ValueBlue = 0;
  }


  if (pos)
  {
    ether.httpServerReply(homePage());
  }
}



void ModoAuto() {

  //Verifica se modo Automático está ativado
  if (ValueSaveAuto == 1)
  {
    //Saida 1
    if (ValueSaida1HrI <= hour && ValueSaida1HrF >= hour)
    {
      digitalWrite(PIN_S1, HIGH);
      EEPROM.write(MemSaida1, 1);
    }
    else
    {
      digitalWrite(PIN_S1, LOW);
      EEPROM.write(MemSaida1, 0);
    }

    //Saida 2
    if (ValueSaida2HrI <= hour && ValueSaida2HrF >= hour)
    {
      digitalWrite(PIN_S2, HIGH);
      EEPROM.write(MemSaida2, 1);
    }
    else
    {
      digitalWrite(PIN_S2, LOW);
      EEPROM.write(MemSaida2, 0);
    }

    //Saida 3
    if (ValueSaida3HrI <= hour && ValueSaida3HrF >= hour)
    {
      digitalWrite(PIN_S3, HIGH);
      EEPROM.write(MemSaida3, 1);
    }
    else
    {
      digitalWrite(PIN_S3, LOW);
      EEPROM.write(MemSaida3, 0);
    }

    //Saida 4
    if (ValueSaida4HrI <= hour && ValueSaida4HrF >= hour)
    {
      digitalWrite(PIN_S4, HIGH);
      EEPROM.write(MemSaida4, 1);
    }
    else
    {
      digitalWrite(PIN_S4, LOW);
      EEPROM.write(MemSaida4, 0);
    }
    //Saida 5
    if (ValueSaida5HrI <= hour && ValueSaida5HrF >= hour)
    {
      digitalWrite(PIN_S5, HIGH);
      EEPROM.write(MemSaida5, 1);
    }
    else
    {
      digitalWrite(PIN_S5, LOW);
      EEPROM.write(MemSaida5, 0);
    }

    //Saida 6
    if (ValueSaida6HrI <= hour && ValueSaida6HrF >= hour)
    {
      digitalWrite(PIN_S6, HIGH);
      EEPROM.write(MemSaida6, 1);
    }
    else
    {
      digitalWrite(PIN_S6, LOW);
      EEPROM.write(MemSaida6, 0);
    }

    //Saida 7
    if (ValueSaida7HrI <= hour && ValueSaida7HrF >= hour)
    {
      digitalWrite(PIN_S7, HIGH);
      EEPROM.write(MemSaida7, 1);
    }
    else
    {
      digitalWrite(PIN_S7, LOW);
      EEPROM.write(MemSaida7, 0);
    }

    //Saida 8
    if (ValueSaida8HrI <= hour && ValueSaida8HrF >= hour)
    {
      digitalWrite(PIN_S8, HIGH);
      EEPROM.write(MemSaida8, 1);
    }
    else
    {
      digitalWrite(PIN_S8, LOW);
      EEPROM.write(MemSaida8, 0);
    }

    //RGB
    if (ValueRGBHrI <= hour && ValueRGBHrF >= hour)
    {
      analogWrite(PIN_RED, ValueRed);
      analogWrite(PIN_GREEN, ValueGreen);
      analogWrite(PIN_BLUE, ValueBlue);
    }
    else
    {
      analogWrite(PIN_RED, 0);
      analogWrite(PIN_GREEN, 0);
      analogWrite(PIN_BLUE, 0);
    }
  }
}




