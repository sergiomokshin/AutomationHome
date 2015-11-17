/*
Sergio Mokshin
AutomaÃ§Ã£o Live - Outubro/2015
*/

#include <SPI.h>
#include <EEPROM.h>
#include "Wire.h"
#include <LiquidCrystal_I2C.h>	// For the LCD

#include "DHT.h"
#define DHTPIN 8
#define DHTTYPE DHT11

//EndereÃ§os de LCDs 1602
LiquidCrystal_I2C lcd(0x3F, 16,2);// set the LCD address to 0x27 for a 16 chars and 2 line display
//LiquidCrystal_I2C lcd(0x27, 16,2);// set the LCD address to 0x27 for a 16 chars and 2 line display
//LiquidCrystal_I2C lcd(0x20,16,2); // set the LCD address to 0x20 for a 16 chars and 2 line display

//EndereÃ§os de LCDs 2004
//LiquidCrystal_I2C lcd(0x27, 20, 4); // set the LCD address to 0x27 for a 20 chars and 4 line display

//Se necessÃ¡rio utilizar um I2C Scanner http://playground.arduino.cc/Main/I2cScanner

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

//Variveis S1
int S1 = 0;       //Status da Saida S1
int MS1HI = 1;    //EndereÃ§o de memoria para conteudo hora inicio Saida 1 
int MS1MI = 2;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 1 
int MS1HF = 3;    //EndereÃ§o de memoria para conteudo hora fim Saida 1 
int MS1MF = 4;    //EndereÃ§o de memoria para conteudo minuto fim Saida 1 
int MTS1 = 5;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S1 
int MPS1 = 6;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 1
int S1HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 1 
int S1MI = 0;     //Conteudo da memoria com minuto inicio Saida 1 
int S1HF = 0;     //Conteudo da memoria com hora fim Saida 1 
int S1MF = 0;     //Conteudo da memoria com minuto fim Saida 1 
char TS1 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S1 (A-Agendado, M- Manual, P-Pulso )
int S1P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 1


//Variveis S2
int S2 = 0;       //Status da Saida S2
int MS2HI = 7;    //EndereÃ§o de memoria para conteudo hora inicio Saida 2 
int MS2MI = 8;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 2 
int MS2HF = 9;    //EndereÃ§o de memoria para conteudo hora fim Saida 2 
int MS2MF = 10;    //EndereÃ§o de memoria para conteudo minuto fim Saida 2 
int MTS2 = 11;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S2 
int MPS2 = 12;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 2
int S2HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 2 
int S2MI = 0;     //Conteudo da memoria com minuto inicio Saida 2 
int S2HF = 0;     //Conteudo da memoria com hora fim Saida 2 
int S2MF = 0;     //Conteudo da memoria com minuto fim Saida 2 
char TS2 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S2 (A-Agendado, M- Manual, P-Pulso )
int S2P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 2

//Variveis S3
int S3 = 0;       //Status da Saida S3
int MS3HI = 13;    //EndereÃ§o de memoria para conteudo hora inicio Saida 3 
int MS3MI = 14;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 3 
int MS3HF = 15;    //EndereÃ§o de memoria para conteudo hora fim Saida 3 
int MS3MF = 16;    //EndereÃ§o de memoria para conteudo minuto fim Saida 3 
int MTS3 = 17;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S3 
int MPS3 = 18;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 3
int S3HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 3 
int S3MI = 0;     //Conteudo da memoria com minuto inicio Saida 3 
int S3HF = 0;     //Conteudo da memoria com hora fim Saida 3 
int S3MF = 0;     //Conteudo da memoria com minuto fim Saida 3 
char TS3 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S3 (A-Agendado, M- Manual, P-Pulso )
int S3P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 3


//Variveis S4
int S4 = 0;       //Status da Saida S4
int MS4HI = 19;    //EndereÃ§o de memoria para conteudo hora inicio Saida 4 
int MS4MI = 20;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 4 
int MS4HF = 21;    //EndereÃ§o de memoria para conteudo hora fim Saida 4 
int MS4MF = 22;    //EndereÃ§o de memoria para conteudo minuto fim Saida 4 
int MTS4 = 23;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S4 
int MPS4 = 24;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 4
int S4HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 4 
int S4MI = 0;     //Conteudo da memoria com minuto inicio Saida 4 
int S4HF = 0;     //Conteudo da memoria com hora fim Saida 4 
int S4MF = 0;     //Conteudo da memoria com minuto fim Saida 4 
char TS4 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S4 (A-Agendado, M- Manual, P-Pulso )
int S4P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 4


//Variveis S5
int S5 = 0;       //Status da Saida S5
int MS5HI = 25;    //EndereÃ§o de memoria para conteudo hora inicio Saida 5 
int MS5MI = 26;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 5 
int MS5HF = 27;    //EndereÃ§o de memoria para conteudo hora fim Saida 5 
int MS5MF = 28;    //EndereÃ§o de memoria para conteudo minuto fim Saida 5 
int MTS5 = 29;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S5 
int MPS5 = 30;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 5
int S5HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 5 
int S5MI = 0;     //Conteudo da memoria com minuto inicio Saida 5 
int S5HF = 0;     //Conteudo da memoria com hora fim Saida 5 
int S5MF = 0;     //Conteudo da memoria com minuto fim Saida 5 
char TS5 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S5 (A-Agendado, M- Manual, P-Pulso )
int S5P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 5


//Variveis S6
int S6 = 0;       //Status da Saida S6
int MS6HI = 31;    //EndereÃ§o de memoria para conteudo hora inicio Saida 6 
int MS6MI = 32;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 6 
int MS6HF = 33;    //EndereÃ§o de memoria para conteudo hora fim Saida 6 
int MS6MF = 34;    //EndereÃ§o de memoria para conteudo minuto fim Saida 6 
int MTS6 = 35;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S6 
int MPS6 = 36;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 6
int S6HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 6 
int S6MI = 0;     //Conteudo da memoria com minuto inicio Saida 6 
int S6HF = 0;     //Conteudo da memoria com hora fim Saida 6 
int S6MF = 0;     //Conteudo da memoria com minuto fim Saida 6 
char TS6 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S6 (A-Agendado, M- Manual, P-Pulso )
int S6P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 6

//Variveis S7
int S7 = 0;       //Status da Saida S7
int MS7HI = 37;    //EndereÃ§o de memoria para conteudo hora inicio Saida 7 
int MS7MI = 38;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 7 
int MS7HF = 39;    //EndereÃ§o de memoria para conteudo hora fim Saida 7 
int MS7MF = 40;    //EndereÃ§o de memoria para conteudo minuto fim Saida 7 
int MTS7 = 41;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S7 
int MPS7 = 42;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 7
int S7HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 7 
int S7MI = 0;     //Conteudo da memoria com minuto inicio Saida 7 
int S7HF = 0;     //Conteudo da memoria com hora fim Saida 7 
int S7MF = 0;     //Conteudo da memoria com minuto fim Saida 7 
char TS7 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S7 (A-Agendado, M- Manual, P-Pulso )
int S7P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 7


//Variveis S8
int S8 = 0;       //Status da Saida S8
int MS8HI = 43;    //EndereÃ§o de memoria para conteudo hora inicio Saida 8 
int MS8MI = 44;    //EndereÃ§o de memoria para conteudo minuto inicio Saida 8 
int MS8HF = 45;    //EndereÃ§o de memoria para conteudo hora fim Saida 8 
int MS8MF = 46;    //EndereÃ§o de memoria para conteudo minuto fim Saida 8 
int MTS8 = 47;     //EndereÃ§o de memoria para conteudo TIPO DE ACIONAMENTO S8 
int MPS8 = 48;     //EndereÃ§o de memoria para conteudo tempo de acionamento do pulso da saida 8
int S8HI = 0;     //Conteudo da memoria com hora inicio  ou  Tempo do pulso em segundos Saida 8 
int S8MI = 0;     //Conteudo da memoria com minuto inicio Saida 8 
int S8HF = 0;     //Conteudo da memoria com hora fim Saida 8 
int S8MF = 0;     //Conteudo da memoria com minuto fim Saida 8 
char TS8 = 'M';   //ConteÃºdo com TIPO DE ACIONAMENTO S8 (A-Agendado, M- Manual, P-Pulso )
int S8P = 0;      //Conteudo da memoria com tempo de acionamento do pulso da saida 8


int SRed = 0; //conteÃºdo com ultimo comando enviado Red
int SGreen = 0; //conteÃºdo com ultimo comando enviado Green
int SBlue = 0; //conteÃºdo com ultimo comando enviado Blue

float umidade;
float temperatura;
int countLcd = 0;

String readString;
int inicioucomando;
String comando = "";

byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;
DHT dht(DHTPIN, DHTTYPE);

void setup() {

  Wire.begin();
  Serial.begin(9600); // for debug

  pinMode(PIN_S1, OUTPUT);
  pinMode(PIN_S2, OUTPUT);
  pinMode(PIN_S3, OUTPUT);
  pinMode(PIN_S4, OUTPUT);
  pinMode(PIN_S5, OUTPUT);
  pinMode(PIN_S6, OUTPUT);
  pinMode(PIN_S7, OUTPUT);
  pinMode(PIN_S8, OUTPUT);

  S1HI = EEPROM.read(MS1HI);
  S1MI = EEPROM.read(MS1MI);
  S1HF = EEPROM.read(MS1HF);
  S1MF = EEPROM.read(MS1MI);
  TS1 =  EEPROM.read(MTS1);
  S1P =  EEPROM.read(MPS1);
  
  S2HI = EEPROM.read(MS2HI);
  S2MI = EEPROM.read(MS2MI);
  S2HF = EEPROM.read(MS2HF);
  S2MF = EEPROM.read(MS2MI);
  TS2 =  EEPROM.read(MTS2);
  S2P =  EEPROM.read(MPS2);
  
  S3HI = EEPROM.read(MS3HI);
  S3MI = EEPROM.read(MS3MI);
  S3HF = EEPROM.read(MS3HF);
  S3MF = EEPROM.read(MS3MI);
  TS3 =  EEPROM.read(MTS3);
  S3P =  EEPROM.read(MPS3);
  
  S4HI = EEPROM.read(MS4HI);
  S4MI = EEPROM.read(MS4MI);
  S4HF = EEPROM.read(MS4HF);
  S4MF = EEPROM.read(MS4MI);
  TS4 =  EEPROM.read(MTS4);
  S4P =  EEPROM.read(MPS4);
  
  S5HI = EEPROM.read(MS5HI);
  S5MI = EEPROM.read(MS5MI);
  S5HF = EEPROM.read(MS5HF);
  S5MF = EEPROM.read(MS5MI);
  TS5 =  EEPROM.read(MTS5);
  S5P =  EEPROM.read(MPS5);
  
  S6HI = EEPROM.read(MS6HI);
  S6MI = EEPROM.read(MS6MI);
  S6HF = EEPROM.read(MS6HF);
  S6MF = EEPROM.read(MS6MI);
  TS6 =  EEPROM.read(MTS6);
  S6P =  EEPROM.read(MPS6);
  
  S7HI = EEPROM.read(MS7HI);
  S7MI = EEPROM.read(MS7MI);
  S7HF = EEPROM.read(MS7HF);
  S7MF = EEPROM.read(MS7MI);
  TS7 =  EEPROM.read(MTS7);
  S7P =  EEPROM.read(MPS7);
  
  S8HI = EEPROM.read(MS8HI);
  S8MI = EEPROM.read(MS8MI);
  S8HF = EEPROM.read(MS8HF);
  S8MF = EEPROM.read(MS8MI);
  TS8 =  EEPROM.read(MTS8);
  S8P =  EEPROM.read(MPS8);
  
  
  lcd.init();
  lcd.begin(16, 2);
  lcd.clear();

  dht.begin();
}

void loop() {
  BuscaDataHora(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);   
  BuscaTemperatura();    
  AguardaComandos();
  ModoAuto(); 
  RetornaDados();
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
    lcd.print("temperatura:");
    lcd.print(temperatura);
    lcd.print("-");
    lcd.print(umidade);
    lcd.print("%");
  }
  else
  {
    lcd.setCursor(0, 1);
    lcd.print("Saidas:");
    lcd.print(S1);
    lcd.print(S2);
    lcd.print(S3);
    lcd.print(S4);
    lcd.print(S5);
    lcd.print(S6);
    lcd.print(S7);
    lcd.print(S8);


    if (SRed > 10 )
      lcd.print("R");
    else
      lcd.print(" ");
    if (SGreen > 10 )
      lcd.print("G");
    else
      lcd.print(" ");

    if (SBlue > 10 )
      lcd.print("B     ");
    else
      lcd.print("      ");
  }
  if (countLcd >= 100)
    countLcd = 0;

}


void AguardaComandos()
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
  //Envio de comando para acionamento de saÃ­das de relÃ©s
  if (comando[0] == 'D')
  {
    int port = (comando[1] - '0');
    int value = (comando[2] - '0');
    if (port == 1)
    {
      S1 = value;
      digitalWrite(PIN_S1, S1);      
    }
    else if (port == 2)
    {
      S2 = value;
      digitalWrite(PIN_S2, S2);      
    }
    else if (port == 3)
    {
      S3 = value;
      digitalWrite(PIN_S3, S3);      
    }
    else if (port == 4)
    {
      S4 = value;
      digitalWrite(PIN_S4, S4);      
    }
    else if (port == 5)
    {
      S5 = value;
      digitalWrite(PIN_S5, S5);    
    }
    else if (port == 6)
    {
      S6 = value;
      digitalWrite(PIN_S6, S6);      
    }
    else if (port == 7)
    {
      S7 = value;
      digitalWrite(PIN_S7, S7);      
    }
    else if (port == 8)
    {
      S8 = value;
      digitalWrite(PIN_S8, S8);
    }
  }
  //Envio de comando para acionamento de saÃ­das de potencia
  else if (comando[0] == 'A')
  {
    int port = (comando[1] - '0');
    int value = (comando[2] - '0');
    value = value * 28;

    if ( port == 6 )
    {
      SRed = value;
      analogWrite(port, value);      
    }
    else if ( port == 5 )
    {
      SGreen = value;
      analogWrite(port, value);      
    }
    else if ( port == 3 )
    {
      SBlue = value;
      analogWrite(port, value);      
    }
  }
  //Envio de comando para agendamento das horas das saÃ­das
  else if (comando[0] == 'H')
  {
    int port = (comando[1] - '0');
    String type = String(comando[2]);
    String hora = String(comando[3]);
    hora += String(comando[4]);
    
    if (port == 1)
    {
      if (type == "I")
      {
        S1HI = hora.toInt();
        EEPROM.write(MS1HI, S1HI);
      }
      else
      {
        S1HF = hora.toInt();
        EEPROM.write(MS1HF, S1HF);
      }
    }

    if (port == 2)
    {
      if (type == "I")
      {
        S2HI = hora.toInt();
        EEPROM.write(MS2HI, S2HI);
      }
      else
      {
        S2HF = hora.toInt();
        EEPROM.write(MS2HF, S2HF);
      }
    }

    if (port == 3)
    {
      if (type == "I")
      {
        S3HI = hora.toInt();
        EEPROM.write(MS3HI, S3HI);
      }
      else
      {
        S3HF = hora.toInt();
        EEPROM.write(MS3HF, S3HF);
      }
    }

    if (port == 4)
    {
      if (type == "I")
      {
        S4HI = hora.toInt();
        EEPROM.write(MS4HI, S4HI);
      }
      else
      {
        S4HF = hora.toInt();
        EEPROM.write(MS4HF, S4HF);
      }
    }

    if (port == 5)
    {
      if (type == "I")
      {
        S5HI = hora.toInt();
        EEPROM.write(MS5HI, S5HI);
      }
      else
      {
        S5HF = hora.toInt();
        EEPROM.write(MS5HF, S5HF);
      }
    }

    if (port == 6)
    {
      if (type == "I")
      {
        S6HI = hora.toInt();
        EEPROM.write(MS6HI, S6HI);
      }
      else
      {
        S6HF = hora.toInt();
        EEPROM.write(MS6HF, S6HF);
      }
    }

    if (port == 7)
    {
      if (type == "I")
      {
        S7HI = hora.toInt();
        EEPROM.write(MS7HI, S7HI);
      }
      else
      {
        S7HF = hora.toInt();
        EEPROM.write(MS7HF, S7HF);
      }
    }

    if (port == 8)
    {
      if (type == "I")
      {
        S8HI = hora.toInt();
        EEPROM.write(MS8HI, S8HI);
      }
      else
      {
        S8HF = hora.toInt();
        EEPROM.write(MS8HF, S8HF);
      }
    }  
  }
  
   //Envio de comando para envio do tipo de saÃ­das
  else if (comando[0] == 'T')
  {
    int port = (comando[1] - '0');
    char tipo = comando[2];
        

    if (port == 1)
    {	
	  TS1 = tipo;
	  EEPROM.write(MTS1, TS1);	       
    } 
	else if (port == 2)
    {	
	  TS2 = tipo;
	  EEPROM.write(MTS2, TS2);	       
    }
	else if (port == 3)
    {	
	  TS3 = tipo;
	  EEPROM.write(MTS3, TS3);	       
    }
	else if (port == 4)
    {	
	  TS4 = tipo;
	  EEPROM.write(MTS4, TS4);	       
    }
	else if (port == 5)
    {	
	  TS5 = tipo;
	  EEPROM.write(MTS5, TS5);	       
    }
	else if (port == 6)
    {	
	  TS6 = tipo;
	  EEPROM.write(MTS6, TS6);	       
    }
	else if (port == 7)
    {	
	  TS7 = tipo;
	  EEPROM.write(MTS7, TS7);	       
    }
	else if (port == 8)
    {	
	  TS8 = tipo;
	  EEPROM.write(MTS8, TS8);	       
    }	
 }
  
  //Envio de comando para agendamento dos minutos das saÃ­das
  else if (comando[0] == 'M')
  {
    int port = (comando[1] - '0');
    String type = String(comando[2]);
    String minuto = String(comando[3]);
    minuto += String(comando[4]);

    if (port == 1)
    {
      if (type == "I")
      {
        S1MI = minuto.toInt();
        EEPROM.write(MS1MI, S1MI);
      }
      else
      {
        S1MF = minuto.toInt();
        EEPROM.write(MS1MF, S1MF);
      }
    }

    if (port == 2)
    {
      if (type == "I")
      {
        S2MI = minuto.toInt();
        EEPROM.write(MS2MI, S2MI);
      }
      else
      {
        S2MF = minuto.toInt();
        EEPROM.write(MS2MF, S2MF);
      }
    }

    if (port == 3)
    {
      if (type == "I")
      {
        S3MI = minuto.toInt();
        EEPROM.write(MS3MI, S3MI);
      }
      else
      {
        S3MF = minuto.toInt();
        EEPROM.write(MS3MF, S3MF);
      }
    }

    if (port == 4)
    {
      if (type == "I")
      {
        S4MI = minuto.toInt();
        EEPROM.write(MS4MI, S4MI);
      }
      else
      {
        S4MF = minuto.toInt();
        EEPROM.write(MS4MF, S4MF);
      }
    }

    if (port == 5)
    {
      if (type == "I")
      {
        S5MI = minuto.toInt();
        EEPROM.write(MS5MI, S5MI);
      }
      else
      {
        S5MF = minuto.toInt();
        EEPROM.write(MS5MF, S5MF);
      }
    }

    if (port == 6)
    {
      if (type == "I")
      {
        S6MI = minuto.toInt();
        EEPROM.write(MS6MI, S6MI);
      }
      else
      {
        S6MF = minuto.toInt();
        EEPROM.write(MS6MF, S6MF);
      }
    }

    if (port == 7)
    {
      if (type == "I")
      {
        S7MI = minuto.toInt();
        EEPROM.write(MS7MI, S7MI);
      }
      else
      {
        S7MF = minuto.toInt();
        EEPROM.write(MS7MF, S7MF);
      }
    }

    if (port == 8)
    {
      if (type == "I")
      {
        S8MI = minuto.toInt();
        EEPROM.write(MS8MI, S8MI);
      }
      else
      {
        S8MF = minuto.toInt();
        EEPROM.write(MS8MF, S8MF);
      }
    }  
  }
  
  //Envio de configuraÃ§Ã£o de tempo do Pulso
  else if (comando[0] == 'B')
  {
	int port = (comando[1] - '0');
    String tempo = String(comando[2]);
    tempo += String(comando[3]);
       	    
    if (port == 1)
    {  
		S1P = tempo.toInt();
        EEPROM.write(MPS1, S1P);
    }
	else if (port == 2)
    {      
		S2P = tempo.toInt();
        EEPROM.write(MPS2, S2P);
    }
	else if (port == 3)
    {      
		S3P = tempo.toInt();
        EEPROM.write(MPS3, S3P); 	 
    }
	else if (port == 4)
    {      
		S4P = tempo.toInt();
        EEPROM.write(MPS4, S4P); 	 
    }
	else if (port == 5)
    {      
	  	S5P = tempo.toInt();
        EEPROM.write(MPS5, S5P); 	  	 
    }
	else if (port == 6)
    {      
		S6P = tempo.toInt();
        EEPROM.write(MPS6, S6P); 	  	 
    }
	else if (port == 7)
    {      
		S7P = tempo.toInt();
        EEPROM.write(MPS7, S7P); 	 	 
    }
	else if (port == 8)
    {      
		S8P = tempo.toInt();
        EEPROM.write(MPS8, S8P); 	  	 
    }
    
      
  }
  
  //Envio de comando de Pulso
  else if (comando[0] == 'P')
  {
    int port = (comando[1] - '0');
    int tempo = 0;
       	    
    if (port == 1)
    {      
	  digitalWrite(PIN_S1, HIGH);      
	  tempo = S1P * 1000;   
	  delay(tempo);
	  digitalWrite(PIN_S1, LOW);      	 
    }
	else if (port == 2)
    {      
	  digitalWrite(PIN_S2, HIGH);      
	  tempo = S2P * 1000;   
	  delay(tempo);
	  digitalWrite(PIN_S2, LOW);      	 
    }
	else if (port == 3)
    {      
	  digitalWrite(PIN_S3, HIGH);    
	  tempo = S3P * 1000;   	  
	  delay(tempo);
	  digitalWrite(PIN_S3, LOW);      	 
    }
	else if (port == 4)
    {      
	  digitalWrite(PIN_S4, HIGH);      
	  tempo = S4P * 1000;   
	  delay(tempo);
	  digitalWrite(PIN_S4, LOW);      	 
    }
	else if (port == 5)
    {      
	  digitalWrite(PIN_S5, HIGH);      
	  tempo = S5P * 1000;   
	  delay(tempo);
	  digitalWrite(PIN_S5, LOW);      	 
    }
	else if (port == 6)
    {      
	  digitalWrite(PIN_S6, HIGH);    
	  tempo = S6P * 1000;   	  
	  delay(tempo);
	  digitalWrite(PIN_S6, LOW);      	 
    }
	else if (port == 7)
    {      
	  digitalWrite(PIN_S7, HIGH);      
	  tempo = S7P * 1000;   
	  delay(tempo);
	  digitalWrite(PIN_S7, LOW);      	 
    }
	else if (port == 8)
    {      
	  digitalWrite(PIN_S8, HIGH);      
	  tempo = S8P * 1000;   
	  delay(tempo);
	  digitalWrite(PIN_S8, LOW);      	 
    }
         
  }
  
  //Envio de comando para alteraÃ§Ã£o de modo Agendado e Manual
  else if (comando[0] == 'M')
  {
   // int modo = (comando[1] - '0');
    //ValueSaveAuto = modo;
   // EEPROM.write(MemAuto, ValueSaveAuto);
  }
  //Envio de comando para alteraÃ§Ã£o de horÃ¡rio da placa
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

void RetornaDados() {

 
  S1 = digitalRead(PIN_S1);
  S2 = digitalRead(PIN_S2);
  S3 = digitalRead(PIN_S3);
  S4 = digitalRead(PIN_S4);
  S5 = digitalRead(PIN_S5);
  S6 = digitalRead(PIN_S6);
  S7 = digitalRead(PIN_S7);
  S8 = digitalRead(PIN_S8);

  String retorno = "";
 
  //retorno += F("HTTP/1.1 200 OK"); //send new page
 // retorno += F("Content-Type: application/json");
  retorno += "dataCB";
  //retorno += F("({");

  retorno += "({";
 
  retorno += "\"Dia\":";
  retorno += dayOfMonth, DEC;
  retorno += ",\"Mes\":";
  retorno += month, DEC;
  retorno += ",\"Ano\":";
  retorno += year, DEC;
  retorno += ",\"Hora\":";
  retorno += hour, DEC;
  retorno += ",\"Minuto\":";
  retorno += minute, DEC;
  retorno += ",\"Segundo\":";
  retorno += second, DEC;

  retorno += ",\"temperatura\":";
  retorno += temperatura;
  retorno += ",\"umidade\":";
  retorno += umidade;

  
  retorno += ",\"S1\":";
  retorno += S1;  
  retorno += ",\"S1HI\":";
  retorno += S1HI;
  retorno += ",\"S1MI\":";
  retorno += S1MI;
  retorno += ",\"S1HF\":";
  retorno += S1HF;
  retorno += ",\"S1MF\":";
  retorno += S1MF;
  retorno += ",\"TS1\":\"";
  retorno += TS1 + "\"";
  retorno += ",\"S1P\":";
  retorno += S1P;

 
  retorno += ",\"S2\":";
  retorno += S2;
  retorno += ",\"S2HI\":";
  retorno += S2HI;
  retorno += ",\"S2MI\":";
  retorno += S2MI;
  retorno += ",\"S2HF\":";
  retorno += S2HF;
  retorno += ",\"S2MF\":";
  retorno += S2MF;
  retorno += ",\"TS2\":\"";
  retorno += TS2 + "\"";
  retorno += ",\"S2P\":";
  retorno += S2P;

  
  retorno += ",\"S3\":";
  retorno += S3;
  retorno += ",\"S3HI\":";
  retorno += S3HI;
  retorno += ",\"S3MI\":";
  retorno += S3MI;
  retorno += ",\"S3HF\":";
  retorno += S3HF;
  retorno += ",\"S3MF\":";
  retorno += S3MF;
  retorno += ",\"TS3\":\"";
  retorno += TS3 + "\"";
  retorno += ",\"S3P\":";
  retorno += S3P;
  
  retorno += ",\"S4\":";
  retorno += S4;
  retorno += ",\"S4HI\":";
  retorno += S4HI;
  retorno += ",\"S4MI\":";
  retorno += S4MI;
  retorno += ",\"S4HF\":";
  retorno += S4HF;
  retorno += ",\"S4MF\":";
  retorno += S4MF;
  retorno += ",\"TS4\":\"";
  retorno += TS4 + "\"";
  retorno += ",\"S4P\":";
  retorno += S4P;
  
  retorno += ",\"S5\":";
  retorno += S5;
  retorno += ",\"S5HI\":";
  retorno += S5HI;
  retorno += ",\"S5MI\":";
  retorno += S5MI;
  retorno += ",\"S5HF\":";
  retorno += S5HF;
  retorno += ",\"S5MF\":";
  retorno += S5MF;
  retorno += ",\"TS5\":\"";
  retorno += TS5 + "\"";
  retorno += ",\"S5P\":";
  retorno += S5P;

  /*
  retorno += ",\"S6\":";
  retorno += S6;
  retorno += ",\"S6HI\":";
  retorno += S6HI;
  retorno += ",\"S6MI\":";
  retorno += S6MI;
  retorno += ",\"S6HF\":";
  retorno += S6HF;
  retorno += ",\"S6MF\":";
  retorno += S6MF;
  retorno += ",\"TS6\":\"";
  retorno += TS6 + "\"";  
  retorno += ",\"S6P\":";
  retorno += S6P;
  
  retorno += ",\"S7\":";   
  retorno += S7;
  retorno += ",\"S7HI\":";
  retorno += S7HI;
  retorno += ",\"S7MI\":";
  retorno += S7MI;
  retorno += ",\"S7HF\":";
  retorno += S7HF;
  retorno += ",\"S7MF\":";
  retorno += S7MF;
  retorno += ",\"TS7\":\"";  
  retorno += TS7 + "\"";
  retorno += ",\"S7P\":";
  retorno += S7P;
    
  
  retorno += ",\"S8\":";  
  retorno += S8;
  retorno += ",\"S8HI\":";
  retorno += S8HI;
  retorno += ",\"S8MI\":";
  retorno += S8MI;
  retorno += ",\"S8HF\":";
  retorno += S8HF;
  retorno += ",\"S8MF\":";
  retorno += S8MF;
  retorno += ",\"TS8\":\"";
  retorno += TS8 + "\"";
  retorno += ",\"S8P\":";
  retorno += S8P;

       
  retorno += ",\"Red\":";
  retorno += SRed;
  retorno += ",\"Green\":";
  retorno += SGreen;
  retorno += ",\"Blue\":";
  retorno += SBlue;

  retorno += ",\"A6\":";
  retorno += analogRead(A6);
  retorno += ",\"A7\":";
  retorno += analogRead(A7);
*/

  retorno += "})";

  //retorno += F("})");

    Serial.print(retorno);

}

void ModoAuto() {


  //Verifica se modo AutomÃ¡tico estÃ¡ ativado para cada SaÃ­da
  if (TS1 == 'A')
  {
    //Saida 1
    if (S1HI <= hour && S1HF >= hour)
    {
	   if(S1HI == S1HF)
	   {
	     if(S1MI <= minute && S1MF >= minute)
		 {
			digitalWrite(PIN_S1, HIGH);
		 }
		 else{
			digitalWrite(PIN_S1, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S1, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S1, LOW);
    }

   }
   else  if (TS2 == 'A')
   {
    //Saida 2
    if (S2HI <= hour && S2HF >= hour)
    {
	   if(S2HI == S2HF)
	   {
	     if(S2MI <= minute && S2MF >= minute)
		 {
			digitalWrite(PIN_S2, HIGH);
		 }
		 else{
			digitalWrite(PIN_S2, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S2, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S2, LOW);
    }
   }
   else  if (TS3 == 'A')
   {
    //Saida 3
    if (S3HI <= hour && S3HF >= hour)
    {
	   if(S3HI == S3HF)
	   {
	     if(S3MI <= minute && S3MF >= minute)
		 {
			digitalWrite(PIN_S3, HIGH);
		 }
		 else{
			digitalWrite(PIN_S3, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S3, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S3, LOW);
    }
   }
   else  if (TS4 == 'A')
   {
    //Saida 4
    if (S4HI <= hour && S4HF >= hour)
    {
	   if(S4HI == S4HF)
	   {
	     if(S4MI <= minute && S4MF >= minute)
		 {
			digitalWrite(PIN_S4, HIGH);
		 }
		 else{
			digitalWrite(PIN_S4, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S4, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S4, LOW);
    }
   }
   else  if (TS5 == 'A')
   {
    //Saida 5
    if (S5HI <= hour && S5HF >= hour)
    {
	   if(S5HI == S5HF)
	   {
	     if(S5MI <= minute && S5MF >= minute)
		 {
			digitalWrite(PIN_S5, HIGH);
		 }
		 else{
			digitalWrite(PIN_S5, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S5, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S5, LOW);
    }
   }
   else  if (TS6 == 'A')
   {
    //Saida 6
    if (S6HI <= hour && S6HF >= hour)
    {
	   if(S6HI == S6HF)
	   {
	     if(S6MI <= minute && S6MF >= minute)
		 {
			digitalWrite(PIN_S6, HIGH);
		 }
		 else{
			digitalWrite(PIN_S6, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S6, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S6, LOW);
    }
   }
   else  if (TS7 == 'A')
   {
    //Saida 7
    if (S7HI <= hour && S7HF >= hour)
    {
	   if(S7HI == S7HF)
	   {
	     if(S7MI <= minute && S7MF >= minute)
		 {
			digitalWrite(PIN_S7, HIGH);
		 }
		 else{
			digitalWrite(PIN_S7, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S7, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S7, LOW);
    }
   }
   else  if (TS8 == 'A')
   {
    //Saida 8
    if (S8HI <= hour && S8HF >= hour)
    {
	   if(S8HI == S8HF)
	   {
	     if(S8MI <= minute && S8MF >= minute)
		 {
			digitalWrite(PIN_S8, HIGH);
		 }
		 else{
			digitalWrite(PIN_S8, LOW);
		 }	   
	   }
	   else
	   {
		  digitalWrite(PIN_S8, HIGH);
	   }		  
    }
    else
    {
      digitalWrite(PIN_S8, LOW);
    }
   }    
}

void BuscaDataHora(byte * second,
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

void BuscaTemperatura()
{
  	//umidade = dht.readumidade();  
	 //temperatura = dht.readtemperaturaerature();

  if(isnan(temperatura))
    temperatura = 0;
  
  if(isnan(umidade))
    umidade = 0;
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




