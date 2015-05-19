/*
  
    http://www.arduinoecia.com.br/2015/03/arduino-modulo-wireless-esp8266.html
 */

#include "Wire.h"
#define DS1307_I2C_ADDRESS 0x68

#include <LiquidCrystal_I2C.h>	// For the LCD
LiquidCrystal_I2C lcd(0x27, 16, 2); // set the LCD address to 0x27 for a 16 chars and 2 line display
#define DS1307_I2C_ADDRESS 0x68


#define PIN_RED 6
#define PIN_GREEN 5
#define PIN_BLUE 3

//Convert normal decimal numbers to binary coded decimal
byte decToBcd(byte val)
{
  return ( (val/10*16) + (val%10) );
}

//Convert binary coded decimal to normal decimal numbers
byte bcdToDec(byte val)
{
  return ( (val/16*10) + (val%16) );
}

// 1) Sets the date and time on the ds1307
// 2) Starts the clock
// 3) Sets hour mode to 24 hour clock
// Assumes you're passing in valid numbers
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

// Gets the date and time from the ds1307
void getDateDs1307(byte *second,
          byte *minute,
          byte *hour,
          byte *dayOfWeek,
          byte *dayOfMonth,
          byte *month,
          byte *year)
{
  // Reset the register pointer
  Wire.beginTransmission(DS1307_I2C_ADDRESS);
  Wire.write(0);
  Wire.endTransmission();
  
  Wire.requestFrom(DS1307_I2C_ADDRESS, 7);

  // A few of these need masks because certain bits are control bits
  *second     = bcdToDec(Wire.read() & 0x7f);
  *minute     = bcdToDec(Wire.read());
  *hour       = bcdToDec(Wire.read() & 0x3f);  // Need to change this if 12 hour am/pm
  *dayOfWeek  = bcdToDec(Wire.read());
  *dayOfMonth = bcdToDec(Wire.read());
  *month      = bcdToDec(Wire.read());
  *year       = bcdToDec(Wire.read());
}


void setup()  
{
  
  lcd.init();
  lcd.begin(20, 4);
  lcd.clear();
  
  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, OUTPUT);
  pinMode(A3, OUTPUT);
  pinMode(2, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(9, OUTPUT);

  digitalWrite(A0, LOW);    
  digitalWrite(A1, LOW);    
  digitalWrite(A2, LOW);    
  digitalWrite(A3, LOW);
  digitalWrite(2, LOW);
  digitalWrite(4, LOW);
  digitalWrite(7, LOW);
  digitalWrite(9, LOW);
       
 
  analogWrite(6, 0);  
  analogWrite(5, 0);     
  analogWrite(3, 0);  
  
  
  byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;
  Wire.begin();
  
  Serial.begin(9600);  
  
  second = 00;
  minute = 14;
  hour = 18;
  dayOfWeek = 1;
  dayOfMonth = 5;
  month = 5;
  year = 15;
  
 setDateDs1307(second, minute, hour, dayOfWeek, dayOfMonth, month, year);
  
}

void loop() 
{    

  MostraData();
  
  digitalWrite(2, HIGH);    
  delay(1000);
  digitalWrite(2, LOW);    
  digitalWrite(4, HIGH);      
  delay(1000);
  digitalWrite(4, LOW);    
  digitalWrite(7, HIGH);      
  delay(1000);
  digitalWrite(7, LOW);    
  digitalWrite(9, HIGH);      
  delay(1000);
  digitalWrite(9, LOW);    
  digitalWrite(A0, HIGH);      
  delay(1000);
  digitalWrite(A0, LOW);    
  digitalWrite(A1, HIGH);      
  delay(1000);
  digitalWrite(A1, LOW);    
  digitalWrite(A2, HIGH);      
  delay(1000);
  digitalWrite(A2, LOW);    
  digitalWrite(A3, HIGH);      
  delay(1000);     
  digitalWrite(A3, LOW);    
  analogWrite(PIN_RED, 255); 
  delay(1000);
  digitalWrite(PIN_RED, 0);    
  analogWrite(PIN_GREEN, 255); 
  delay(1000);
  digitalWrite(PIN_GREEN, 0);    
  analogWrite(PIN_BLUE, 255); 
  delay(1000);
  analogWrite(PIN_BLUE, 255);    
  analogWrite(PIN_RED, 255);    
  analogWrite(PIN_GREEN, 255);      
  delay(1000);
  analogWrite(PIN_BLUE, 0);    
  analogWrite(PIN_RED, 0);    
  analogWrite(PIN_GREEN, 0);    


 int valA4 = analogRead(A4); 
 Serial.print("A4=");            
 Serial.println(valA4);            
 
  int valA5 = analogRead(A5); 
 Serial.print("A5=");            
 Serial.println(valA5);            
 
  int valA6 = analogRead(A6); 
 Serial.print("A6=");            
 Serial.println(valA6);            
 
 int valA7 = analogRead(A7); 
 Serial.print("A7=");            
 Serial.println(valA7);            
 
 
 int valD2 = digitalRead(2); 
 Serial.print("D2=");            
 Serial.println(valD2);            
   
 int valD5 = digitalRead(5); 
 Serial.print("D5=");            
 Serial.println(valD5);            
 
 int valD6 = digitalRead(6); 
 Serial.print("D6=");            
 Serial.println(valD6);            
  
 int valD8 = digitalRead(8); 
 Serial.print("D8=");            
 Serial.println(valD8);            
  
 int valD10 = digitalRead(10); 
 Serial.print("D10=");            
 Serial.println(valD10);            
 
 int valD11 = digitalRead(11); 
 Serial.print("D11=");            
 Serial.println(valD11);            
 
 int valD12 = digitalRead(12); 
 Serial.print("D12=");            
 Serial.println(valD12);            
 
 int valD13 = digitalRead(13); 
 Serial.print("D13=");            
 Serial.println(valD13);            
    
  
  
}


void MostraData(){
    
  byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;
  getDateDs1307(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);            

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

  
  Serial.print(hour, DEC);
  Serial.print(":");
  Serial.print(minute, DEC);
  Serial.print(":");
  Serial.print(second, DEC);
  Serial.print("  ");
  Serial.print(dayOfMonth, DEC);
  Serial.print("/");
  Serial.print(month, DEC);
  Serial.print("/");
  Serial.println(year, DEC);
  Serial.print("Day of week ");
  Serial.println(dayOfWeek, DEC);
}



  

