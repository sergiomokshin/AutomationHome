/*
  Software serial multple serial test
    
 */

#define PIN_RED 6
#define PIN_GREEN 5
#define PIN_BLUE 3

void setup()  
{

  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, OUTPUT);
  pinMode(A3, OUTPUT);
  pinMode(A6, OUTPUT);
  pinMode(A7, OUTPUT);
  
  Serial.begin(9600);  
  analogWrite(6, 0);  
  analogWrite(5, 0);    
  analogWrite(3, 0);  
  
  
}

void loop() 
{    


  digitalWrite(2, HIGH);      
  delay(500);
  digitalWrite(2, LOW);    
  digitalWrite(4, HIGH);      
  delay(500);
  digitalWrite(4, LOW);    
  digitalWrite(7, HIGH);      
  delay(500);
  digitalWrite(7, LOW);    
  digitalWrite(9, HIGH);      
  delay(500);
  digitalWrite(9, LOW);    
  
  digitalWrite(A0, HIGH);    
  delay(500);
  digitalWrite(A0, LOW);    
  digitalWrite(A1, HIGH);      
  delay(500);
  digitalWrite(A1, LOW);    
  digitalWrite(A2, HIGH);      
  delay(500);
  digitalWrite(A2, LOW);    
  digitalWrite(A3, HIGH);      
  delay(500);
  digitalWrite(A3, LOW); 



     


  
  
  digitalWrite(8, HIGH);      
  delay(500);
  digitalWrite(8, LOW);    
  digitalWrite(10, HIGH);      
  delay(500);
  digitalWrite(10, LOW);    
  digitalWrite(11, HIGH);      
  delay(500);
  digitalWrite(11, LOW);    
  digitalWrite(12, HIGH);      
  delay(500);
  digitalWrite(12, LOW);    
  digitalWrite(13, HIGH);      
  delay(500);
  digitalWrite(13, LOW);    


  digitalWrite(A6, HIGH);      
  delay(500);
  digitalWrite(A6, LOW);    
  digitalWrite(A7, HIGH);      
  delay(500);
  digitalWrite(A7, LOW);    
  
  analogWrite(PIN_RED, 255); 
  delay(500);
  digitalWrite(PIN_RED, 0);    
  analogWrite(PIN_GREEN, 255); 
  delay(500);
  digitalWrite(PIN_GREEN, 0);    
  analogWrite(PIN_BLUE, 255); 
  delay(500);
  analogWrite(PIN_BLUE, 255);    
  analogWrite(PIN_RED, 255);    
  analogWrite(PIN_GREEN, 255);      
  delay(500);
  analogWrite(PIN_BLUE, 0);    
  analogWrite(PIN_RED, 0);    
  analogWrite(PIN_GREEN, 0);      
    
  
  
}



  
