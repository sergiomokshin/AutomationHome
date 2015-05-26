/*
Sergio Mokshin
Automação Live - Abril/2015

init()                              Init the LCD and library functions, Clears the display, turns off the cursors
commandWrite(command)      Write a command not supported yet in the library to the display
position(Row, Column)      Move the cursor to position valid range is Row[0-3], Column[0,19]
print(char)                        Send the single character char to the display at the cursor position.
println(string)                  Send the String to the display at the cursor position.
clear()                        Clear the display and place the cursor at 0,0
home()                        Home the cursor to 0,0 but do not clear the display
on()                              Turn the LCD display on
off()                              Turn the LCD display off
cursor_on()                  Turn the blinking line cursor on
cursor_off()                  Turn the blinking line cursor off
blink_on()                        Turn the blinking block cursor on
blink_off()                        Turn the blinking block cursor on
left()                              Move the cursor to the left
right()                        Move the cursor to the right
keypad()                        Read a value from the keypad. Returns 0 if no key press is in the buffer


*/

#include <SPI.h>
#include <Ethernet.h>
#include <EEPROM.h>
#include "Wire.h"
#include <LiquidCrystal_I2C.h>	// For the LCD

#include "DHT.h"
#define DHTPIN 8
#define DHTTYPE DHT11

LiquidCrystal_I2C lcd(0x27, 16, 2); // set the LCD address to 0x27 for a 16 chars and 2 line display
#define DS1307_I2C_ADDRESS 0x68

byte mac[] = { 0x90, 0xA2, 0xDA, 0x0D, 0xA6, 0x09 };
byte ip[] = { 192, 168, 0, 202 };
byte gateway[] = { 192, 168, 0, 1 };
byte subnet[] = { 255, 255, 255, 0 };
EthernetServer server(80);

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

float humidity;
float temp;


int countLcd = 0;

String readString;
byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;
DHT dht(DHTPIN, DHTTYPE);

void setup() {

  Wire.begin();

  Ethernet.begin(mac, ip, gateway, subnet);
  server.begin();
  Serial.begin(9600); // for debug

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

  lcd.init();
  lcd.begin(20, 4);
  lcd.clear();

  dht.begin();
}

void loop() {

  getDateDs1307(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);
  humidity = dht.readHumidity();
  temp = dht.readTemperature();

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
 

  WebServer();
  ModoAuto();
  PrintLcd();
}

void PrintLcd() {

  lcd.backlight();

  lcd.setCursor(0, 0);
  if (dayOfMonth < 10)
    lcd.print("0");
  lcd.print(dayOfMonth);
  lcd.print("/");
  if (month < 10)
    lcd.print("0");
  lcd.print(month);
  lcd.print("/");
  lcd.print(year);
  lcd.print(" ");
  if (hour < 10)
    lcd.print("0");
  lcd.print(hour);
  lcd.print(":");
  if (minute < 10)
    lcd.print("0");
  lcd.print(minute);
  lcd.print(":");
  if (second < 10)
    lcd.print("0");
  lcd.print(second);

  countLcd++;
  

  if (countLcd <= 50)
  {

    lcd.setCursor(0, 1);
    lcd.print("Temp:");
    lcd.print(temp);
    lcd.print("-");
    lcd.print(humidity);
    lcd.print("%");
  }
  else
  {
    lcd.setCursor(0, 1);
    lcd.print("Saidas:");
    lcd.print(ValueSaveSaida1);
    lcd.print(ValueSaveSaida2);
    lcd.print(ValueSaveSaida3);
    lcd.print(ValueSaveSaida4);
    lcd.print(ValueSaveSaida5);
    lcd.print(ValueSaveSaida6);
    lcd.print(ValueSaveSaida7);    
    lcd.print(ValueSaveSaida6);        


    if (ValueRed > 10 )
      lcd.print("R");
    else
      lcd.print(" ");
    if (ValueGreen > 10 )
      lcd.print("G");
    else
      lcd.print(" ");

    if (ValueBlue > 10 )
      lcd.print("B     ");
    else
      lcd.print("      ");
  }
 
  if(countLcd >=100)
    countLcd = 0;

}

void WebServer() {

  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        if (readString.length() < 100) {
          readString += c;
          //Serial.print(c);
        }

        //if HTTP request has ended
        if (c == '\n') {
          //Serial.println(readString);

          if (readString.indexOf("?AUTOL") > 0) {
            ValueSaveAuto = 1;
            EEPROM.write(MemAuto, 1);
          }

          if (readString.indexOf("?AUTOD") > 0) {
            ValueSaveAuto = 0;
            EEPROM.write(MemAuto, 0);
          }

          if (readString.indexOf("?DataHora") > 0) {

            String Data = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y"));
            String Horario = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z"));

            //  Serial.println(Data);
            //  Serial.println(Horario);

            //Data no formato dd/mm/yyyy
            String Dia = Data.substring(0, Data.indexOf("/"));
            String temp = Data.substring(Data.indexOf("/"));
            String Mes = temp.substring(1, 3);
            String Ano = temp.substring(temp.lastIndexOf("/") + 3);

            String Hora = Horario.substring(0, Horario.indexOf(":"));
            String temp2 = Horario.substring(Horario.indexOf(":"));
            String Minuto = temp2.substring(1, 3);

            //Serial.println(Hora);
            //Serial.println(Minuto);

           // Serial.println(Dia);
           // Serial.println(Mes);
           // Serial.println(Ano);


            second = 0;
            minute = Minuto.toInt();
            hour = Hora.toInt();
            dayOfWeek = 1;
            dayOfMonth = Dia.toInt();
            month = Mes.toInt();
            year = Ano.toInt();

           // Serial.println(dayOfMonth);
           // Serial.println(month);
           // Serial.println(year);

            setDateDs1307(second, minute, hour, dayOfWeek, dayOfMonth, month, year);
          }


          if (readString.indexOf("?S1L") > 0) {
            digitalWrite(PIN_S1, HIGH);
            EEPROM.write(MemSaida1, 1);
          }
          if (readString.indexOf("?S1D") > 0) {
            digitalWrite(PIN_S1, LOW);
            EEPROM.write(MemSaida1, 0);
          }

          if (readString.indexOf("?S2L") > 0) {
            digitalWrite(PIN_S2, HIGH);
            EEPROM.write(MemSaida2, 1);
          }
          if (readString.indexOf("?S2D") > 0) {
            digitalWrite(PIN_S2, LOW);
            EEPROM.write(MemSaida2, 0);
          }

          if (readString.indexOf("?S3L") > 0) {
            digitalWrite(PIN_S3, HIGH);
            EEPROM.write(MemSaida3, 1);
          }
          if (readString.indexOf("?S3D") > 0) {
            digitalWrite(PIN_S3, LOW);
            EEPROM.write(MemSaida3, 0);
          }

          if (readString.indexOf("?S4L") > 0) {
            digitalWrite(PIN_S4, HIGH);
            EEPROM.write(MemSaida4, 1);
          }
          if (readString.indexOf("?S4D") > 0) {
            digitalWrite(PIN_S4, LOW);
            EEPROM.write(MemSaida4, 0);
          }
          
          if (readString.indexOf("?S5L") > 0) {
            digitalWrite(PIN_S5, HIGH);
            EEPROM.write(MemSaida5, 1);
          }
          if (readString.indexOf("?S5D") > 0) {
            digitalWrite(PIN_S5, LOW);
            EEPROM.write(MemSaida5, 0);
          }
          
          
          if (readString.indexOf("?S6L") > 0) {
            digitalWrite(PIN_S6, HIGH);
            EEPROM.write(MemSaida6, 1);
          }
          if (readString.indexOf("?S6D") > 0) {
            digitalWrite(PIN_S6, LOW);
            EEPROM.write(MemSaida6, 0);
          }
          
          
          if (readString.indexOf("?S7L") > 0) {
            digitalWrite(PIN_S7, HIGH);
            EEPROM.write(MemSaida7, 1);
          }
          if (readString.indexOf("?S7D") > 0) {
            digitalWrite(PIN_S7, LOW);
            EEPROM.write(MemSaida7, 0);
          }
          
          
          if (readString.indexOf("?S8L") > 0) {
            digitalWrite(PIN_S8, HIGH);
            EEPROM.write(MemSaida8, 1);
          }
          if (readString.indexOf("?S8D") > 0) {
            digitalWrite(PIN_S8, LOW);
            EEPROM.write(MemSaida8, 0);
          }

          if (readString.indexOf("?RED") > 0) {
            ValueRed = 255;
            ValueGreen = 0;
            ValueBlue = 0;
            if (ValueSaveAuto == 0)
            {
              analogWrite(PIN_RED, ValueRed);
              analogWrite(PIN_GREEN, ValueGreen);
              analogWrite(PIN_BLUE, ValueBlue);
            }
            EEPROM.write(MemRed, ValueRed);
            EEPROM.write(MemGreen, ValueGreen);
            EEPROM.write(MemBlue, ValueBlue);
          }

          if (readString.indexOf("?GRE") > 0) {
            ValueRed = 0;
            ValueGreen = 255;
            ValueBlue = 0;
            if (ValueSaveAuto == 0)
            {
              analogWrite(PIN_RED, ValueRed);
              analogWrite(PIN_GREEN, ValueGreen);
              analogWrite(PIN_BLUE, ValueBlue);
            }
            EEPROM.write(MemRed, ValueRed);
            EEPROM.write(MemGreen, ValueGreen);
            EEPROM.write(MemBlue, ValueBlue);
          }

          if (readString.indexOf("?BLU") > 0) {
            ValueRed = 0;
            ValueGreen = 0;
            ValueBlue = 255;
            if (ValueSaveAuto == 0)
            {
              analogWrite(PIN_RED, ValueRed);
              analogWrite(PIN_GREEN, ValueGreen);
              analogWrite(PIN_BLUE, ValueBlue);
            }
            EEPROM.write(MemRed, ValueRed);
            EEPROM.write(MemGreen, ValueGreen);
            EEPROM.write(MemBlue, ValueBlue);
          }

          if (readString.indexOf("?WHI") > 0) {
            ValueRed = 255;
            ValueGreen = 255;
            ValueBlue = 255;
            if (ValueSaveAuto == 0)
            {
              analogWrite(PIN_RED, ValueRed);
              analogWrite(PIN_GREEN, ValueGreen);
              analogWrite(PIN_BLUE, ValueBlue);
            }
            EEPROM.write(MemRed, ValueRed);
            EEPROM.write(MemGreen, ValueGreen);
            EEPROM.write(MemBlue, ValueBlue);
          }

          if (readString.indexOf("?RGBOFF") > 0) {
            ValueRed = 0;
            ValueGreen = 0;
            ValueBlue = 0;
            analogWrite(PIN_RED, ValueRed);
            analogWrite(PIN_GREEN, ValueGreen);
            analogWrite(PIN_BLUE, ValueBlue);
            EEPROM.write(MemRed, ValueRed);
            EEPROM.write(MemGreen, ValueGreen);
            EEPROM.write(MemBlue, ValueBlue);
          }

          if (readString.indexOf("?AgeS1HrI") > 0) {

            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida1HrI, cmd);
            ValueSaida1HrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida1HrF, cmd);
            ValueSaida1HrF = cmd;

          }

          if (readString.indexOf("?AgeS2HrI") > 0) {

            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida2HrI, cmd);
            ValueSaida2HrI = cmd;
            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida2HrF, cmd);
            ValueSaida2HrF = cmd;
          }

          if (readString.indexOf("?AgeS3HrI") > 0) {

            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida3HrI, cmd);
            ValueSaida3HrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida3HrF, cmd);
            ValueSaida3HrF = cmd;
          }
          
          if (readString.indexOf("?AgeS4HrI") > 0) {
            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida4HrI, cmd);
            ValueSaida4HrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida4HrF, cmd);
            ValueSaida4HrF = cmd;
          }
          
          if (readString.indexOf("?AgeS5HrI") > 0) {
            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida5HrI, cmd);
            ValueSaida5HrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida5HrF, cmd);
            ValueSaida5HrF = cmd;
          }
          
          if (readString.indexOf("?AgeS6HrI") > 0) {
            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida6HrI, cmd);
            ValueSaida6HrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida6HrF, cmd);
            ValueSaida6HrF = cmd;
          }
          
          if (readString.indexOf("?AgeS7HrI") > 0) {
            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida7HrI, cmd);
            ValueSaida7HrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida7HrF, cmd);
            ValueSaida7HrF = cmd;
          }
          
          if (readString.indexOf("?AgeS8HrI") > 0) {
            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemSaida8HrI, cmd);
            ValueSaida8HrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemSaida8HrF, cmd);
            ValueSaida8HrF = cmd;
          }


          if (readString.indexOf("?AgeRGBHrI") > 0) {
            int cmd = readString.substring(readString.indexOf("y") + 1, readString.lastIndexOf("y")).toInt();
            EEPROM.write(MemRGBHrI, cmd);
            ValueRGBHrI = cmd;

            cmd = readString.substring(readString.indexOf("z") + 1, readString.lastIndexOf("z")).toInt();
            EEPROM.write(MemRGBHrF, cmd);
            ValueRGBHrF = cmd;
          }

          SendResponse(client);
          delay(1);
          client.stop();
          readString = "";

        }
      }
    }
  }

}


void SendResponse(EthernetClient client) {

  ModoAuto(); // Atualizar variaveis


  ValueSaveSaida1 = digitalRead(PIN_S1);
  ValueSaveSaida2 = digitalRead(PIN_S2);
  ValueSaveSaida3 = digitalRead(PIN_S3);
  ValueSaveSaida4 = digitalRead(PIN_S4);
  ValueSaveSaida5 = digitalRead(PIN_S5);
  ValueSaveSaida6 = digitalRead(PIN_S6);
  ValueSaveSaida7 = digitalRead(PIN_S7);  
  ValueSaveSaida8 = digitalRead(PIN_S8);    

  int LedR = analogRead(6);
  int LedG = analogRead(5);
  int LedB = analogRead(3);
  


 client.println(F("HTTP/1.1 200 OK")); //send new page
  client.println(F("Content-Type: application/json"));
  client.println();
  client.print("dataCB");
  client.println(F("({"));

  client.print("\"Auto\":\"");
  client.print(ValueSaveAuto);
  client.println("\"");


  client.print(",\"Day\":");
  client.println(dayOfMonth, DEC);
  client.print(",\"Mounth\":");
  client.println(month, DEC);
  client.print(",\"Year\":");
  client.println(year, DEC);
  client.print(",\"Hour\":");
  client.println(hour, DEC);
  client.print(",\"Minute\":");
  client.println(minute, DEC);
  client.print(",\"Second\":");
  client.println(second, DEC);
  

  client.print(",\"temp\":");
  client.println(temp);
  client.print(",\"humidity\":");
  client.println(humidity);
  

  client.print(",\"S1\":");
  client.println(ValueSaveSaida1);
  client.print(",\"S2\":");
  client.println(ValueSaveSaida2);
  client.print(",\"S3\":");
  client.println(ValueSaveSaida3);
  client.print(",\"S4\":");
  client.println(ValueSaveSaida4);
  client.print(",\"S5\":");
  client.println(ValueSaveSaida5);
  client.print(",\"S6\":");
  client.println(ValueSaveSaida6);
  client.print(",\"S7\":");
  client.println(ValueSaveSaida7);
  client.print(",\"S8\":");
  client.println(ValueSaveSaida8);

  client.print(",\"AgeS1HrI\":");
  client.println(ValueSaida1HrI);
  client.print(",\"AgeS1HrF\":");
  client.println(ValueSaida1HrF);
  client.print(",\"AgeS2HrI\":");
  client.println(ValueSaida2HrI);
  client.print(",\"AgeS2HrF\":");
  client.println(ValueSaida2HrF);
  client.print(",\"AgeS3HrI\":");
  client.println(ValueSaida3HrI);
  client.print(",\"AgeS3HrF\":");
  client.println(ValueSaida3HrF);
  client.print(",\"AgeS4HrI\":");
  client.println(ValueSaida4HrI);
  client.print(",\"AgeS4HrF\":");
  client.println(ValueSaida4HrF);
  client.print(",\"AgeS5HrI\":");
  client.println(ValueSaida5HrI);
  client.print(",\"AgeS5HrF\":");
  client.println(ValueSaida5HrF);
  client.print(",\"AgeS6HrI\":");
  client.println(ValueSaida6HrI);
  client.print(",\"AgeS6HrF\":");
  client.println(ValueSaida6HrF);
  client.print(",\"AgeS7HrI\":");
  client.println(ValueSaida7HrI);
  client.print(",\"AgeS7HrF\":");
  client.println(ValueSaida7HrF);
  client.print(",\"AgeS8HrI\":");
  client.println(ValueSaida8HrI);
  client.print(",\"AgeS8HrF\":");
  client.println(ValueSaida8HrF);

  client.print(",\"AgeRGBHrI\":");
  client.println(ValueRGBHrI);
  client.print(",\"AgeRGBHrF\":");
  client.println(ValueRGBHrF);

  client.print(",\"Red\":");
  client.println(LedR);
  client.print(",\"Green\":");
  client.println(LedG);
  client.print(",\"Blue\":");
  client.println(LedB);


  client.println(F("})"));
  client.println();


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

