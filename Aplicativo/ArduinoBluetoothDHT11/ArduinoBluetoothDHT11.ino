/*
Sergio Mokshin
Automação Live - Abril/2015

  VCC 3V
  GND  GND
  TX  RX
  RX  TX
  Protocolo
  Exemplos protocolo

  |D21|  Liga Saida 1
  |D20|  Desliga Saida 1
  |A64| Potencia nível 4 pino PIN_GREEN

  |H1I03|  Horario Inicio 1 03
  |H1F22|  Horario Fim 1 	22


  |M1| Modo Agendado
  |M0| Modo Manual


  |Ty12/06/2015yz22:35z|    Horario


*/

#include <EEPROM.h>
#include <SPI.h>
#include <EEPROM.h>
//#include <OneWire.h> // Importar biblioteca!!!!!!!
#include "Wire.h"
#include <LiquidCrystal_I2C.h>	// For the LCD

#include "DHT.h"
#define DHTPIN 8
#define DHTTYPE DHT11

//Endereços de LCDs 1602
//LiquidCrystal_I2C lcd(0x3F, 16,2);// set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd(0x27, 16,2);// set the LCD address to 0x27 for a 16 chars and 2 line display
//LiquidCrystal_I2C lcd(0x20,16,2); // set the LCD address to 0x20 for a 16 chars and 2 line display

//Endereços de LCDs 2004
//LiquidCrystal_I2C lcd(0x27, 20, 4); // set the LCD address to 0x27 for a 20 chars and 4 line display

//Se necessário utilizar um I2C Scanner http://playground.arduino.cc/Main/I2cScanner

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
int ValueSaveRed = 0; //Conteudo de memoria  Red
int ValueSaveGreen = 0; //Conteudo de memoria  Green
int ValueSaveBlue = 0; //Conteudo de memoria  Blue

int inicioucomando;
String comando = "";
boolean EEPROMComandos = false;

byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;


float humidity;
float temp;
DHT dht(DHTPIN, DHTTYPE);

int countLcd = 0;


void setup()
{
  //An EEPROM write takes 3.3 ms to complete. The EEPROM memory has a specified life of 100,000 write/erase cycles, so you may need to be careful about how often you write to it.
  //https://www.arduino.cc/en/Reference/EEPROMWrite
  //Manter em EPPROM acionamentos manuais do usuário  		
  //EEPROMComandos = true;
    EEPROMComandos = false;

  Wire.begin();
  int countLcd = 0;

  pinMode(PIN_S1, OUTPUT);
  pinMode(PIN_S2, OUTPUT);
  pinMode(PIN_S3, OUTPUT);
  pinMode(PIN_S4, OUTPUT);
  pinMode(PIN_S5, OUTPUT);
  pinMode(PIN_S6, OUTPUT);
  pinMode(PIN_S7, OUTPUT);
  pinMode(PIN_S8, OUTPUT);

  inicioucomando = 0;
  comando = "";

  Serial.begin(9600);

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

  if (EEPROMComandos == true)
  {
    //Inicializando placa com valores armazenados na FLASH Memory
    ValueSaveSaida1 = EEPROM.read(MemSaida1);
    ValueSaveSaida2 = EEPROM.read(MemSaida2);
    ValueSaveSaida3 = EEPROM.read(MemSaida3);
    ValueSaveSaida4 = EEPROM.read(MemSaida4);
    ValueSaveSaida5 = EEPROM.read(MemSaida5);
    ValueSaveSaida6 = EEPROM.read(MemSaida6);
    ValueSaveSaida7 = EEPROM.read(MemSaida7);
    ValueSaveSaida8 = EEPROM.read(MemSaida8);
    ValueSaveRed = EEPROM.read(MemRed);
    ValueSaveGreen = EEPROM.read(MemGreen);
    ValueSaveBlue = EEPROM.read(MemBlue);

    digitalWrite(PIN_S1, ValueSaveSaida1);
    digitalWrite(PIN_S2, ValueSaveSaida2);
    digitalWrite(PIN_S3, ValueSaveSaida3);
    digitalWrite(PIN_S4, ValueSaveSaida4);
    digitalWrite(PIN_S5, ValueSaveSaida5);
    digitalWrite(PIN_S6, ValueSaveSaida6);
    digitalWrite(PIN_S7, ValueSaveSaida7);
    digitalWrite(PIN_S8, ValueSaveSaida8);

    analogWrite(PIN_RED, ValueSaveRed);
    analogWrite(PIN_GREEN, ValueSaveGreen);
    analogWrite(PIN_BLUE, ValueSaveBlue);
  }

  lcd.init();
  lcd.begin(16, 2);
  lcd.clear();

  second = 00;
  minute = 00;
  hour = 20;
  dayOfWeek = 3;
  dayOfMonth = 28;
  month = 6;
  year = 15;

  dht.begin();

  //DEBUG
  //Comentar novamente apos ajuste manual da data
  //setDateDs1307(second, minute, hour, dayOfWeek, dayOfMonth, month, year);

}

void loop()
{
  ///SEMPRE DESLIGAR BLUETOOTH OU TX / RX DURANTE UPLOAD
  getDateDs1307(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);

  humidity = dht.readHumidity();  
  temp = dht.readTemperature();

  if(isnan(temp))
    temp = 0;
  
  if(isnan(humidity))
    humidity = 0;  

  aguardacomandos();
  RetornaComandos();
  ModoAuto();
  PrintLcd();
}

void aguardacomandos()
{
  char ch;
  while (Serial.available()) {
    ch = Serial.read();
    if (ch == '|')
    {
      if (inicioucomando == 1)
      {
        inicioucomando = 0;
        disparacomando();
      }
      else
      {
        inicioucomando = 1;
      }
    }
    else
    {
      comando.concat(ch);
    }
  }
}


void disparacomando()
{

  Serial.println(comando);
  //Envio de comando para acionamento de saídas de relés
  if (comando[0] == 'D')
  {
    int port = (comando[1] - '0');
    int value = (comando[2] - '0');

    if (port == 1)
    {
      ValueSaveSaida1 = value;
      digitalWrite(PIN_S1, ValueSaveSaida1);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida1, ValueSaveSaida1);
      }
    }
    else if (port == 2)
    {
      ValueSaveSaida2 = value;
      digitalWrite(PIN_S2, ValueSaveSaida2);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida2, ValueSaveSaida2);
      }
    }
    else if (port == 3)
    {
      ValueSaveSaida3 = value;
      digitalWrite(PIN_S3, ValueSaveSaida3);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida3, ValueSaveSaida3);
      }
    }
    else if (port == 4)
    {
      ValueSaveSaida4 = value;
      digitalWrite(PIN_S4, ValueSaveSaida4);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida4, ValueSaveSaida4);
      }
    }
    else if (port == 5)
    {
      ValueSaveSaida5 = value;
      digitalWrite(PIN_S5, ValueSaveSaida5);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida5, ValueSaveSaida5);
      }
    }
    else if (port == 6)
    {
      ValueSaveSaida6 = value;
      digitalWrite(PIN_S6, ValueSaveSaida6);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida6, ValueSaveSaida6);
      }
    }
    else if (port == 7)
    {
      ValueSaveSaida7 = value;
      digitalWrite(PIN_S7, ValueSaveSaida7);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida7, ValueSaveSaida7);
      }
    }
    else if (port == 8)
    {
      ValueSaveSaida8 = value;
      digitalWrite(PIN_S8, ValueSaveSaida8);
      if (EEPROMComandos)
      {
        EEPROM.write(MemSaida8, ValueSaveSaida8);
      }
    }
  }
  //Envio de comando para acionamento de saídas de potencia
  else if (comando[0] == 'A')
  {
    int port = (comando[1] - '0');
    int value = (comando[2] - '0');
    value = value * 28;

    if ( port == 6 )
    {
      ValueSaveRed = value;
      analogWrite(port, value);
      if (EEPROMComandos)
      {
        EEPROM.write(MemRed, ValueSaveRed);
      }
    }
    else if ( port == 5 )
    {
      ValueSaveGreen = value;
      analogWrite(port, value);
      if (EEPROMComandos)
      {
        EEPROM.write(MemGreen, ValueSaveGreen);
      }
    }
    else if ( port == 3 )
    {
      ValueSaveBlue = value;
      analogWrite(port, value);
      if (EEPROMComandos)
      {
        EEPROM.write(MemBlue, ValueSaveBlue);
      }
    }
  }
  //Envio de comando para agendamento de saídas
  else if (comando[0] == 'H')
  {

    int port = (comando[1] - '0');
    String type = String(comando[2]);
    String hora = String(comando[3]);
    hora += String(comando[4]);

    lcd.setCursor(0, 3);
    lcd.print(comando);
    lcd.setCursor(0, 4);
    lcd.print(hora);

    if (port == 1)
    {
      if (type == "I")
      {
        ValueSaida1HrI = hora.toInt();
        EEPROM.write(MemSaida1HrI, ValueSaida1HrI);
      }
      else
      {
        ValueSaida1HrF = hora.toInt();
        EEPROM.write(MemSaida1HrF, ValueSaida1HrF);
      }
    }

    if (port == 2)
    {
      if (type == "I")
      {
        ValueSaida2HrI = hora.toInt();
        EEPROM.write(MemSaida2HrI, ValueSaida2HrI);
      }
      else
      {
        ValueSaida2HrF = hora.toInt();
        EEPROM.write(MemSaida2HrF, ValueSaida2HrF);
      }
    }

    if (port == 3)
    {
      if (type == "I")
      {
        ValueSaida3HrI = hora.toInt();
        EEPROM.write(MemSaida3HrI, ValueSaida3HrI);
      }
      else
      {
        ValueSaida3HrF = hora.toInt();
        EEPROM.write(MemSaida3HrF, ValueSaida3HrF);
      }
    }

    if (port == 4)
    {
      if (type == "I")
      {
        ValueSaida4HrI = hora.toInt();
        EEPROM.write(MemSaida4HrI, ValueSaida4HrI);
      }
      else
      {
        ValueSaida4HrF = hora.toInt();
        EEPROM.write(MemSaida4HrF, ValueSaida4HrF);
      }
    }

    if (port == 5)
    {
      if (type == "I")
      {
        ValueSaida5HrI = hora.toInt();
        EEPROM.write(MemSaida5HrI, ValueSaida5HrI);
      }
      else
      {
        ValueSaida5HrF = hora.toInt();
        EEPROM.write(MemSaida5HrF, ValueSaida5HrF);
      }
    }

    if (port == 6)
    {
      if (type == "I")
      {
        ValueSaida6HrI = hora.toInt();
        EEPROM.write(MemSaida6HrI, ValueSaida6HrI);
      }
      else
      {
        ValueSaida6HrF = hora.toInt();
        EEPROM.write(MemSaida6HrF, ValueSaida6HrF);
      }
    }

    if (port == 7)
    {
      if (type == "I")
      {
        ValueSaida7HrI = hora.toInt();
        EEPROM.write(MemSaida7HrI, ValueSaida7HrI);
      }
      else
      {
        ValueSaida7HrF = hora.toInt();
        EEPROM.write(MemSaida7HrF, ValueSaida7HrF);
      }
    }

    if (port == 8)
    {
      if (type == "I")
      {
        ValueSaida8HrI = hora.toInt();
        EEPROM.write(MemSaida8HrI, ValueSaida8HrI);
      }
      else
      {
        ValueSaida8HrF = hora.toInt();
        EEPROM.write(MemSaida8HrF, ValueSaida8HrF);
      }
    }

    if (port == 9)
    {
      if (type == "I")
      {
        ValueRGBHrI = hora.toInt();
        EEPROM.write(MemRGBHrI, ValueRGBHrI);
      }
      else
      {
        ValueRGBHrF = hora.toInt();
        EEPROM.write(MemRGBHrF, ValueRGBHrF);
      }
    }

  }
  //Envio de comando para alteração de modo Agendado e Manual
  else if (comando[0] == 'M')
  {
    int modo = (comando[1] - '0');
    ValueSaveAuto = modo;
    EEPROM.write(MemAuto, ValueSaveAuto);
  }
  //Envio de comando para alteração de horário da placa
  else if (comando[0] == 'T') {

    String Data = comando.substring(comando.indexOf("y") + 1, comando.lastIndexOf("y"));
    String Horario = comando.substring(comando.indexOf("z") + 1, comando.lastIndexOf("z"));

    //Serial.println(Data);
    //Serial.println(Horario);

    String Dia = Data.substring(0, Data.indexOf("/"));
    String temp = Data.substring(Data.indexOf("/"));
    String Mes = temp.substring(1, 3);
    String Ano = temp.substring(temp.lastIndexOf("/") + 1); //YY

    Serial.println(Dia);
    Serial.println(Mes);
    Serial.println(Ano);

    String Hora = Horario.substring(0, Horario.indexOf(":"));
    String temp2 = Horario.substring(Horario.indexOf(":"));
    String Minuto = temp2.substring(1, 3);

    //Serial.println(Hora);
    //Serial.println(Minuto);

    second = 0;
    minute = Minuto.toInt();
    hour = Hora.toInt();
    dayOfWeek = 1;
    dayOfMonth = Dia.toInt();
    month = Mes.toInt();
    year = Ano.toInt();
    setDateDs1307(second, minute, hour, dayOfWeek, dayOfMonth, month, year);
  }
  else
  {
    Serial.println("Invalid Command");
  }
  comando = "";
}

void RetornaComandos()
{
  String retorno = "|COMANDOS#";
  retorno.concat(ValueSaveSaida1);
  retorno.concat("#");
  retorno.concat(ValueSaveSaida2);
  retorno.concat("#");
  retorno.concat(ValueSaveSaida3);
  retorno.concat("#");
  retorno.concat(ValueSaveSaida4);
  retorno.concat("#");
  retorno.concat(ValueSaveSaida5);
  retorno.concat("#");
  retorno.concat(ValueSaveSaida6);
  retorno.concat("#");
  retorno.concat(ValueSaveSaida7);
  retorno.concat("#");
  retorno.concat(ValueSaveSaida8);
  retorno.concat("#");
  retorno.concat(ValueSaveRed);
  retorno.concat("#");
  retorno.concat(ValueSaveGreen);
  retorno.concat("#");
  retorno.concat(ValueSaveBlue);
  retorno.concat("#");
  retorno.concat(ValueSaveAuto);
  retorno.concat("#");
  retorno.concat(ValueSaida1HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida1HrF);
  retorno.concat("#");
  retorno.concat(ValueSaida2HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida2HrF);
  retorno.concat("#");
  retorno.concat(ValueSaida3HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida3HrF);
  retorno.concat("#");
  retorno.concat(ValueSaida4HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida4HrF);
  retorno.concat("#");
  retorno.concat(ValueSaida5HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida5HrF);
  retorno.concat("#");
  retorno.concat(ValueSaida6HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida6HrF);
  retorno.concat("#");
  retorno.concat(ValueSaida7HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida7HrF);
  retorno.concat("#");
  retorno.concat(ValueSaida8HrI);
  retorno.concat("#");
  retorno.concat(ValueSaida8HrF);
  retorno.concat("#");
  retorno.concat(ValueRGBHrI);
  retorno.concat("#");
  retorno.concat(ValueRGBHrF);
  retorno.concat("#");
  retorno.concat(String(dayOfMonth) + "/" + String(month) + "/" + String(year));
  retorno.concat("#");
  retorno.concat(String(hour) + ":" + String(minute) + ":" + String(second));

  Serial.println(retorno);
}


void ModoAuto() {

  if (ValueSaveAuto == 1)
  {

    //Saida 1
    if (ValueSaida1HrI <= hour && ValueSaida1HrF >= hour)
    {
      ValueSaveSaida1 = HIGH;
      digitalWrite(PIN_S1, HIGH);
    }
    else
    {
      ValueSaveSaida1 = LOW;
      digitalWrite(PIN_S1, LOW);
    }

    //Saida 2
    if (ValueSaida2HrI <= hour && ValueSaida2HrF >= hour)
    {
      ValueSaveSaida2 = HIGH;
      digitalWrite(PIN_S2, HIGH);
    }
    else
    {
      ValueSaveSaida2 = LOW;
      digitalWrite(PIN_S2, LOW);
    }

    //Saida 3
    if (ValueSaida3HrI <= hour && ValueSaida3HrF >= hour)
    {
      ValueSaveSaida3 = HIGH;
      digitalWrite(PIN_S3, HIGH);
    }
    else
    {
      ValueSaveSaida3 = LOW;
      digitalWrite(PIN_S3, LOW);
    }

    //Saida 4
    if (ValueSaida4HrI <= hour && ValueSaida4HrF >= hour)
    {
      ValueSaveSaida4 = HIGH;
      digitalWrite(PIN_S4, HIGH);
    }
    else
    {
      ValueSaveSaida4 = LOW;
      digitalWrite(PIN_S4, LOW);
    }

    //Saida 5
    if (ValueSaida5HrI <= hour && ValueSaida5HrF >= hour)
    {
      ValueSaveSaida5 = HIGH;
      digitalWrite(PIN_S5, HIGH);
    }
    else
    {
      ValueSaveSaida5 = LOW;
      digitalWrite(PIN_S5, LOW);
    }

    //Saida 6
    if (ValueSaida6HrI <= hour && ValueSaida6HrF >= hour)
    {
      ValueSaveSaida6 = HIGH;
      digitalWrite(PIN_S6, HIGH);
    }
    else
    {
      ValueSaveSaida6 = LOW;
      digitalWrite(PIN_S6, LOW);
    }

    //Saida 7
    if (ValueSaida7HrI <= hour && ValueSaida7HrF >= hour)
    {
      ValueSaveSaida7 = HIGH;
      digitalWrite(PIN_S7, HIGH);
    }
    else
    {
      ValueSaveSaida7 = LOW;
      digitalWrite(PIN_S7, LOW);
    }

    //Saida 8
    if (ValueSaida8HrI <= hour && ValueSaida8HrF >= hour)
    {
      ValueSaveSaida8 = HIGH;
      digitalWrite(PIN_S8, HIGH);
    }
    else
    {
      ValueSaveSaida8 = LOW;
      digitalWrite(PIN_S8, LOW);
    }


    //RGB
    if (ValueRGBHrI <= hour && ValueRGBHrF >= hour)
    {
      analogWrite(PIN_RED, ValueSaveRed);
      analogWrite(PIN_GREEN, ValueSaveGreen);
      analogWrite(PIN_BLUE, ValueSaveBlue);
    }

  }
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
  //  lcd.print("/");
  //  lcd.print(year);
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
    if (ValueSaveSaida1 == 1 )
    lcd.print("1");
  else
    lcd.print(" ");

  if (ValueSaveSaida2 == 1 )
    lcd.print("2");
  else
    lcd.print(" ");

  if (ValueSaveSaida3 == 1 )
    lcd.print("3");
  else
    lcd.print(" ");

  if (ValueSaveSaida4 == 1 )
    lcd.print("4");
  else
    lcd.print(" ");

  if (ValueSaveSaida5 == 1 )
    lcd.print("5");
  else
    lcd.print(" ");

  if (ValueSaveSaida6 == 1 )
    lcd.print("6");
  else
    lcd.print(" ");

  if (ValueSaveSaida7 == 1 )
    lcd.print("7");
  else
    lcd.print(" ");

  if (ValueSaveSaida8 == 1 )
    lcd.print("8");
  else
    lcd.print(" ");


    if (ValueSaveRed > 10 )
      lcd.print("R");
    else
      lcd.print(" ");
    if (ValueSaveGreen > 10 )
      lcd.print("G");
    else
      lcd.print(" ");

    if (ValueSaveBlue > 10 )
      lcd.print("B     ");
    else
      lcd.print("      ");
  }

  if (countLcd >= 100)
    countLcd = 0;

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



