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

//#include "DHT.h"
//#define DHTPIN 8
//#define DHTTYPE DHT11

LiquidCrystal_I2C lcd(0x27, 16, 2); // set the LCD address to 0x27 for a 16 chars and 2 line display
#define DS1307_I2C_ADDRESS 0x68

byte mac[] = { 0x90, 0xA2, 0xDA, 0x0D, 0xA6, 0x09 };
byte ip[] = { 192, 168, 0, 202 };
byte gateway[] = { 192, 168, 0, 1 };
byte subnet[] = { 255, 255, 255, 0 };
EthernetServer server(80);

#define PIN_RED 3
#define PIN_GREEN 6
#define PIN_BLUE 5

int MemSaida1 = 1; //Endereço de memoria com conteudo ultimo comando enviado S1
int MemSaida2 = 2; //Endereço de memoria com conteudo ultimo comando enviado S2
int MemSaida3 = 3; //Endereço de memoria com conteudo ultimo comando enviado S3
int MemSaida4 = 4; //Endereço de memoria com conteudo ultimo comando enviado S4
int MemAuto   = 5; //Endereço de memoria com conteudo ultimo comando enviado modo Automatico/Manual

int MemSaida1HrI = 6; //Endereço de memoria com conteudo inicio horario Saida 3
int MemSaida1HrF = 7; //Endereço de memoria com conteudo fim horario Saida 3
int MemSaida2HrI = 8; //Endereço de memoria com conteudo inicio horario Saida 3
int MemSaida2HrF = 9; //Endereço de memoria com conteudo fim horario Saida 3
int MemSaida3HrI = 10; //Endereço de memoria com conteudo inicio horario Saida 3
int MemSaida3HrF = 11; //Endereço de memoria com conteudo fim horario Saida 3
int MemSaida4HrI = 12; //Endereço de memoria com conteudo inicio horario Saida 4
int MemSaida4HrF = 13; //Endereço de memoria com conteudo fim horario Saida 4

int MemRGBHrI = 14; //Endereço de memoria com conteudo inicio horario RGB
int MemRGBHrF = 15; //Endereço de memoria com conteudo fim horario RGB
int MemRGBType = 16; //Endereço de memoria com conteudo inicio horario RGB com cor Azul
int MemRGBBLUEHrF = 17; //Endereço de memoria com conteudo fim horario RGB com cor Azul

int MemRed = 18; //Endereço de memoria com ultimo comando enviado Red
int MemGreen = 19; //Endereço de memoria com ultimo comando enviado Green
int MemBlue = 20; //Endereço de memoria com ultimo comando enviado Blue

int ValueSaveSaida1 = 0; //Conteudo da memoria com status Saida 1
int ValueSaveSaida2 = 0; //Conteudo da memoria com status Saida 2
int ValueSaveSaida3 = 0; //Conteudo da memoria com status Saida 3
int ValueSaveSaida4 = 0; //Conteudo da memoria com status Saida 4
int ValueSaveAuto   = 0; //Conteudo da memoria com status Automatico ou  Manual

int ValueSaida1HrI = 0; //Conteudo da memoria com inicio horario Saida 3
int ValueSaida1HrF = 0; //Conteudo da memoria fim horario Saida 3
int ValueSaida2HrI = 0; //Conteudo da memoria com inicio horario Saida 3
int ValueSaida2HrF = 0; //Conteudo da memoria fim horario Saida 3
int ValueSaida3HrI = 0; //Conteudo da memoria com inicio horario Saida 3
int ValueSaida3HrF = 0; //Conteudo da memoria fim horario Saida 3
int ValueSaida4HrI = 0; //Conteudo da memoria inicio horario Saida 4
int ValueSaida4HrF = 0; //Conteudo da memoria fim horario Saida 4
int ValueRGBHrI = 0; //Conteudo de memoria inicio horario RGB
int ValueRGBHrF = 0; //Conteudo de memoria fim horario RGB
int ValueRGBType = 0; //Conteudo de memoria com cor RGB para agendamento
int ValueRed = 0; //Conteudo de memoria  Red
int ValueGreen = 0; //Conteudo de memoria  Green
int ValueBlue = 0; //Conteudo de memoria  Blue

float humidity;
float temp;

int S1 = 0;
int S2 = 0;
int S3 = 0;
int S4 = 0;
int LedR = 0;
int LedG = 0;
int LedB = 0;

int countLcd = 0;

String readString;
byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;

//DHT dht(DHTPIN, DHTTYPE);

void setup() {

  Wire.begin();

  Ethernet.begin(mac, ip, gateway, subnet);
  server.begin();
  Serial.begin(9600); // for debug

  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, OUTPUT);
  pinMode(A3, OUTPUT);

  //Inicializando placa com valores armazenados na FLASH Memory
  digitalWrite(A0, EEPROM.read(MemSaida1));
  digitalWrite(A1, EEPROM.read(MemSaida2));
  digitalWrite(A2, EEPROM.read(MemSaida3));
  digitalWrite(A3, EEPROM.read(MemSaida4));
  ValueSaveAuto = EEPROM.read(MemAuto);

  ValueSaida1HrI = EEPROM.read(MemSaida1HrI);
  ValueSaida1HrF = EEPROM.read(MemSaida1HrF);
  ValueSaida2HrI = EEPROM.read(MemSaida2HrI);
  ValueSaida2HrF = EEPROM.read(MemSaida2HrF);
  ValueSaida3HrI = EEPROM.read(MemSaida3HrI);
  ValueSaida3HrF = EEPROM.read(MemSaida3HrF);
  ValueSaida4HrI = EEPROM.read(MemSaida4HrI);
  ValueSaida4HrF = EEPROM.read(MemSaida4HrF);
  ValueRGBHrI = EEPROM.read(MemRGBHrI);
  ValueRGBHrF = EEPROM.read(MemRGBHrF);
  ValueRGBType = EEPROM.read(MemRGBType);
  ValueRed = EEPROM.read(MemRed);
  ValueGreen = EEPROM.read(MemGreen);
  ValueBlue = EEPROM.read(MemBlue);

  analogWrite(PIN_RED, ValueRed);
  analogWrite(PIN_GREEN, ValueGreen);
  analogWrite(PIN_BLUE, ValueBlue);

  lcd.init();
  lcd.begin(20, 4);
  lcd.clear();

//  dht.begin();
}

void loop() {

  getDateDs1307(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);
  //humidity = dht.readHumidity();
  //temp = dht.readTemperature();

  S1 = digitalRead(A0);
  S2 = digitalRead(A1);
  S3 = digitalRead(A2);
  S4 = digitalRead(A3);

  LedR = analogRead(6);
  LedG = analogRead(5);
  LedB = analogRead(3);

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
    if (S1 == 1 )
      lcd.print("1");
    else
      lcd.print(" ");
    if (S2 == 1 )
      lcd.print("2");
    else
      lcd.print(" ");
    if (S3 == 1 )
      lcd.print("3");
    else
      lcd.print(" ");
    if (S4 == 1 )
      lcd.print("4");
    else
      lcd.print(" ");

    if (LedR > 10 )
      lcd.print("R");
    else
      lcd.print(" ");
    if (LedG > 10 )
      lcd.print("G");
    else
      lcd.print(" ");

    if (LedB > 10 )
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
            digitalWrite(A0, HIGH);
            EEPROM.write(MemSaida1, 1);
          }
          if (readString.indexOf("?S1D") > 0) {
            digitalWrite(A0, LOW);
            EEPROM.write(MemSaida1, 0);
          }

          if (readString.indexOf("?S2L") > 0) {
            digitalWrite(A1, HIGH);
            EEPROM.write(MemSaida2, 1);
          }
          if (readString.indexOf("?S2D") > 0) {
            digitalWrite(A1, LOW);
            EEPROM.write(MemSaida2, 0);
          }

          if (readString.indexOf("?S3L") > 0) {
            digitalWrite(A2, HIGH);
            EEPROM.write(MemSaida3, 1);
          }
          if (readString.indexOf("?S3D") > 0) {
            digitalWrite(A2, LOW);
            EEPROM.write(MemSaida3, 0);
          }

          if (readString.indexOf("?S4L") > 0) {
            digitalWrite(A3, HIGH);
            EEPROM.write(MemSaida4, 1);
          }
          if (readString.indexOf("?S4D") > 0) {
            digitalWrite(A3, LOW);
            EEPROM.write(MemSaida4, 0);
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

       //     Serial.println(ValueSaida1HrI);
       //     Serial.println(ValueSaida1HrF);

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
  S1 = digitalRead(A0);
  S2 = digitalRead(A1);
  S3 = digitalRead(A2);
  S4 = digitalRead(A3);

  LedR = analogRead(6);
  LedG = analogRead(5);
  LedB = analogRead(3);


    String bloqueiaAcao = "";
    client.println(F("<html><head><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css'><style>th{background-color: #3E4551;color: #FFFFFF;}</style>"));
    client.println(F("</head><body><div class='container'><div class='row'>"));
    client.println(F("<table class='table table-bordered'><tr><th width=140px>Modo:</th><th colspan=2><label class='radio-inline'>"));

    client.print(F("<input type='radio' name='rdModo' id='rdModoM' value='M' onclick='document.location.href=\"/?AUTOD\"' "));
    if (ValueSaveAuto == 0)
    {
      client.print(F(" checked"));
    }
    client.println(F("> Manual</label>"));

    client.print(F("<label class='radio-inline'><input type='radio' name='rdModo' id='rdModoA' value='A' onclick='document.location.href=\"/?AUTOL\"' "));
    if (ValueSaveAuto == 1)
    {
      client.print(F(" checked"));
      bloqueiaAcao = " disabled ";
    }

    client.println(F(">Agendado</label></th><tr>"));
    client.print(F("<tr><td>Horario Placa </td><td> <input type='text' style='width:140px;' id='txtdt' value='"));
    if (dayOfMonth < 10)
      client.print(F("0"));

    client.print(dayOfMonth, DEC);
    client.print(F("/"));
    if (month < 10)
      client.print(F("0"));

    client.print(month, DEC);
    client.print(F("/20"));
    if (year < 10)
      client.print(F("0"));
    client.print(year, DEC);
    client.print(F("'> "));
    client.print(F("<input type='text' style='width:60px;' id='txthr' value='"));
    if (hour < 10)
      client.print(F("0"));

    client.print(hour, DEC);
    client.print(F(":"));
    if (minute < 10)
      client.print(F("0"));
    client.print(minute, DEC);
    client.print(F("'> <button type='button' id='b1' class='btn btn-info' onclick='javascript:AlteraHr();'>Alterar</button></td>"));
    client.println(F("<tr><th width=140px>Saida</th><th colspan=2>Acao</th></tr>"));


/*
    //Inicio S1
    if (S1 == 1)
    {
      client.println(F("<tr><td>Saida 1 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S1D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 1 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S1L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td><td><input type='number' style='width:40px;' id='txtS1I' min='1' max='23' value='"));
    client.print(ValueSaida1HrI, DEC);
    client.print(F("'><label for = 'S1I'>:00 &nbsp;ate&nbsp;</label><input type='number' style='width:40px;' id='txtS1F' min='1' max='23' value='"));
    client.print(ValueSaida1HrF, DEC);
    client.print(F("'><label for = 'S1I'>:59</label>&nbsp;<button type='button' id='btS1A' class='btn btn-info' onclick='javascript:AlteraAg(\"S1\")'>Alterar</button></td></tr>"));
    //FIM S1

    //Inicio S2
    if (S2 == 1)
    {
      client.println(F("<tr><td>Saida 2 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S2D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 2 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S2L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td><td><input type='number' style='width:40px;' id='txtS2I' min='2' max='23' value='"));
    client.print(ValueSaida2HrI, DEC);
    client.print(F("'><label for = 'S2I'>:00 &nbsp;ate&nbsp;</label><input type='number' style='width:40px;' id='txtS2F' min='2' max='23' value='"));
    client.print(ValueSaida2HrF, DEC);
    client.print(F("'><label for = 'S2I'>:59</label>&nbsp;<button type='button' id='btS2A' class='btn btn-info' onclick='javascript:AlteraAg(\"S2\")'>Alterar</button></td></tr>"));
    //FIM S2

    //Inicio S3
    if (S3 == 1)
    {
      client.println(F("<tr><td>Saida 3 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S3D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 3 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S3L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td><td><input type='number' style='width:40px;' id='txtS3I' min='3' max='33' value='"));
    client.print(ValueSaida3HrI, DEC);
    client.print(F("'><label for = 'S3I'>:00 &nbsp;ate&nbsp;</label><input type='number' style='width:40px;' id='txtS3F' min='3' max='33' value='"));
    client.print(ValueSaida3HrF, DEC);
    client.print(F("'><label for = 'S3I'>:59</label>&nbsp;<button type='button' id='btS3A' class='btn btn-info' onclick='javascript:AlteraAg(\"S3\")'>Alterar</button></td></tr>"));
    //FIM S3

    //Inicio S4
    if (S4 == 1)
    {
      client.println(F("<tr><td>Saida 4 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S4D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 4 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S4L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td><td><input type='number' style='width:40px;' id='txtS4I' min='1' max='23' value='"));
    client.print(ValueSaida4HrI, DEC);
    client.print(F("'><label for = 'S4I'>:00 &nbsp;ate&nbsp;</label><input type='number' style='width:40px;' id='txtS4F' min='1' max='23' value='"));
    client.print(ValueSaida4HrF, DEC);
    client.print(F("'><label for = 'S4I'>:59</label>&nbsp;<button type='button' id='btS4A' class='btn btn-info' onclick='javascript:AlteraAg(\"S4\")'>Alterar</button></td></tr>"));
    //FIM S4


    //Inicio RGB
    client.println(F("<tr><td>RGB - Ligado</td><td>"));

    //Inicio RED
    client.print(F("<a class='btn btn"));
    if (ValueRed > 0 && ValueBlue == 0 && ValueGreen == 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?RED' type='button' >Red</button>"));
    //FIM RED

    //Inicio Green
    client.print(F("<a class='btn btn"));
    if (ValueRed == 0 && ValueBlue == 0 && ValueGreen > 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?GREEN' type='button' >Green</button>"));
    //FIM Green

    //Inicio Blue
    client.print(F("<a class='btn btn"));
    if (ValueRed == 0 && ValueBlue > 0 && ValueGreen == 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?BLUE' type='button' >Blue</button>"));
    //FIM Blue

    //Inicio White
    client.print(F("<a class='btn btn"));
    if (ValueRed > 0 && ValueBlue > 0 && ValueGreen > 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?WHITE' type='button' >White</button>"));
    //FIM Blue

    //Inicio OFF
    client.print(F("<a class='btn btn"));
    if (ValueRed == 0 && ValueBlue == 0 && ValueGreen == 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));

    client.print(bloqueiaAcao);
    client.print(F(" href='/?RGBOFF' type='button' >Desligar</button>"));
    //FIM Blue

    client.print(F("</td><td><input type='number' style='width:40px;' id='txtRGBI' min='4' max='44' value='"));
    client.print(ValueRGBHrI, DEC);
    client.print(F("'><label for = 'RGBI'>:00 &nbsp;ate&nbsp;</label><input type='number' style='width:40px;' id='txtRGBF' min='4' max='44' value='"));
    client.print(ValueRGBHrF, DEC);
    client.print(F("'><label for = 'RGBI'>:59</label>&nbsp;<button type='button' id='btRGBA' class='btn btn-info' onclick='javascript:AlteraAg(\"RGB\")'>Alterar</button>"));
    client.print(F("</td></tr>"));
    //FIM RGB
*/


  //Inicio S1
    if (S1 == 1)
    {
      client.println(F("<tr><td>Saida 1 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S1D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 1 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S1L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td></tr>"));
    //FIM S1

    //Inicio S2
    if (S2 == 1)
    {
      client.println(F("<tr><td>Saida 2 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S2D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 2 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S2L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td></tr>"));
    //FIM S2

    //Inicio S3
    if (S3 == 1)
    {
      client.println(F("<tr><td>Saida 3 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S3D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 3 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S3L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td></tr>"));
    //FIM S3

    //Inicio S4
    if (S4 == 1)
    {
      client.println(F("<tr><td>Saida 4 - Ligada</td><td>"));
      client.println(F("<a class='btn btn-danger' href='/?S4D' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Desligar</button>"));
    }
    else
    {
      client.println(F("<tr><td>Saida 4 - Desligada</td><td>"));
      client.println(F("<a class='btn btn-success' href='/?S4L' type='button' "));
      client.print(bloqueiaAcao);
      client.print(F(">Ligar</button>"));

    }

    client.print(F("</td></tr>"));
    //FIM S4


    //Inicio RGB
    client.println(F("<tr><td>RGB - Ligado</td><td>"));

    //Inicio RED
    client.print(F("<a class='btn btn"));
    if (ValueRed > 0 && ValueBlue == 0 && ValueGreen == 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?RED' type='button' >Red</button>"));
    //FIM RED

    //Inicio Green
    client.print(F("<a class='btn btn"));
    if (ValueRed == 0 && ValueBlue == 0 && ValueGreen > 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?GREEN' type='button' >Green</button>"));
    //FIM Green

    //Inicio Blue
    client.print(F("<a class='btn btn"));
    if (ValueRed == 0 && ValueBlue > 0 && ValueGreen == 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?BLUE' type='button' >Blue</button>"));
    //FIM Blue

    //Inicio White
    client.print(F("<a class='btn btn"));
    if (ValueRed > 0 && ValueBlue > 0 && ValueGreen > 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));
    client.print(F(" href='/?WHITE' type='button' >White</button>"));
    //FIM Blue

    //Inicio OFF
    client.print(F("<a class='btn btn"));
    if (ValueRed == 0 && ValueBlue == 0 && ValueGreen == 0 )
      client.print(F("-danger'"));
    else
      client.print(F("-success'"));

    client.print(bloqueiaAcao);
    client.print(F(" href='/?RGBOFF' type='button' >Desligar</button>"));
    //FIM Blue

    client.print(F("</td></tr>"));
    
    
    client.println(F("</table><script>"));
    client.println(F("function AlteraHr() {"));
    client.println(F("var da = document.getElementById(\"txtdt\").value;"));
    client.println(F("var hr = document.getElementById(\"txthr\").value;"));
    client.println(F("var cmd = \"/?DataHora\" + \"y\" + da + \"yz\" + hr + \"z\";"));
    client.println(F("location.href = cmd;}"));
    client.println(F("function AlteraAg(saida) {"));
    client.println(F("var hrI = document.getElementById(\"txt\" + saida + \"I\").value;"));
    client.println(F("var hrF = document.getElementById(\"txt\" + saida + \"F\").value;"));
    client.println(F("var cmd = \"/?Age\" + saida + \"HrI\" + \"y\" + hrI + \"yz\" + hrF + \"z\";"));
    client.println(F("location.href = cmd;}"));
    client.println(F("</script><html>"));

    client.println();



}

void ModoAuto() {

  //Verifica se modo Automático está ativado
  if (ValueSaveAuto == 1)
  {
    //Saida 1
    if (ValueSaida1HrI <= hour && ValueSaida1HrF >= hour)
    {
      digitalWrite(A0, HIGH);
      EEPROM.write(MemSaida1, 1);
    }
    else
    {
      digitalWrite(A0, LOW);
      EEPROM.write(MemSaida1, 0);
    }

    //Saida 2
    if (ValueSaida2HrI <= hour && ValueSaida2HrF >= hour)
    {
      digitalWrite(A1, HIGH);
      EEPROM.write(MemSaida2, 1);
    }
    else
    {
      digitalWrite(A1, LOW);
      EEPROM.write(MemSaida2, 0);
    }

    //Saida 3
    if (ValueSaida3HrI <= hour && ValueSaida3HrF >= hour)
    {
      digitalWrite(A2, HIGH);
      EEPROM.write(MemSaida3, 1);
    }
    else
    {
      digitalWrite(A2, LOW);
      EEPROM.write(MemSaida3, 0);
    }

    //Saida 4
    if (ValueSaida4HrI <= hour && ValueSaida4HrF >= hour)
    {
      digitalWrite(A3, HIGH);
      EEPROM.write(MemSaida4, 1);
    }
    else
    {
      digitalWrite(A3, LOW);
      EEPROM.write(MemSaida4, 0);
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

