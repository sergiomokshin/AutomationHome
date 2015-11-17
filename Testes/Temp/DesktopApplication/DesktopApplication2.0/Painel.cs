using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO.Ports;
using System.Json;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace DesktopApplication
{
    public partial class Painel : Form
    {
        private string COM;
        private SerialPort port;
        delegate void SetTextCallback(string text);
        private string comandoRecebido;
			
		private int S1 = 0; 		
		private int S1HI = 0;     //Hora inicio  
		private int S1MI = 0;     //Minuto inicio Saida 1 
		private int S1HF = 0;     //Hora fim Saida 1 
		private int S1MF = 0;     //Minuto fim Saida 1 
		private string TS1 = "M";  //TIPO DE ACIONAMENTO S1 (A-Agendado, M- Manual, P-Pulso )
		private int S1P = 0;      //Tempo de acionamento do pulso da saida 1
		
		private int S2 = 0; 		
		private int S2HI = 0;     //Hora inicio  
		private int S2MI = 0;     //Minuto inicio Saida 2 
		private int S2HF = 0;     //Hora fim Saida 2 
		private int S2MF = 0;     //Minuto fim Saida 2 
		private string TS2 = "M";  //TIPO DE ACIONAMENTO S2 (A-Agendado, M- Manual, P-Pulso )
		private int S2P = 0;      //Tempo de acionamento do pulso da saida 2

		private int S3 = 0; 		
		private int S3HI = 0;     //Hora inicio  
		private int S3MI = 0;     //Minuto inicio Saida 3 
		private int S3HF = 0;     //Hora fim Saida 3 
		private int S3MF = 0;     //Minuto fim Saida 3 
		private string TS3 = "M";  //TIPO DE ACIONAMENTO S3 (A-Agendado, M- Manual, P-Pulso )
		private int S3P = 0;      //Tempo de acionamento do pulso da saida 3
		
		private int S4 = 0; 		
		private int S4HI = 0;     //Hora inicio  
		private int S4MI = 0;     //Minuto inicio Saida 4 
		private int S4HF = 0;     //Hora fim Saida 4 
		private int S4MF = 0;     //Minuto fim Saida 4 
		private string TS4 = "M";  //TIPO DE ACIONAMENTO S4 (A-Agendado, M- Manual, P-Pulso )
		private int S4P = 0;      //Tempo de acionamento do pulso da saida 4
		
		private int S5 = 0; 		
		private int S5HI = 0;     //Hora inicio  
		private int S5MI = 0;     //Minuto inicio Saida 5 
		private int S5HF = 0;     //Hora fim Saida 5 
		private int S5MF = 0;     //Minuto fim Saida 5 
		private string TS5 = "M";  //TIPO DE ACIONAMENTO S5 (A-Agendado, M- Manual, P-Pulso )
		private int S5P = 0;      //Tempo de acionamento do pulso da saida 5
		
		private int S6 = 0; 		
		private int S6HI = 0;     //Hora inicio  
		private int S6MI = 0;     //Minuto inicio Saida 6 
		private int S6HF = 0;     //Hora fim Saida 6 
		private int S6MF = 0;     //Minuto fim Saida 6 
		private string TS6 = "M";  //TIPO DE ACIONAMENTO S6 (A-Agendado, M- Manual, P-Pulso )
		private int S6P = 0;      //Tempo de acionamento do pulso da saida 6
		
		private int S7 = 0; 		
		private int S7HI = 0;     //Hora inicio  
		private int S7MI = 0;     //Minuto inicio Saida 7 
		private int S7HF = 0;     //Hora fim Saida 7 
		private int S7MF = 0;     //Minuto fim Saida 7 
		private string TS7 = "M";  //TIPO DE ACIONAMENTO S7 (A-Agendado, M- Manual, P-Pulso )
		private int S7P = 0;      //Tempo de acionamento do pulso da saida 7
		
		private int S8 = 0; 		
		private int S8HI = 0;     //Hora inicio  
		private int S8MI = 0;     //Minuto inicio Saida 8 
		private int S8HF = 0;     //Hora fim Saida 8 
		private int S8MF = 0;     //Minuto fim Saida 8 
		private string TS8 = "M";  //TIPO DE ACIONAMENTO S8 (A-Agendado, M- Manual, P-Pulso )
		private int S8P = 0;      //Tempo de acionamento do pulso da saida 8
		
        
		
        private string SRed;
        private string SGreen;
        private string SBlue;
        
        
        private string Data;
        private string Hora;


        public Painel()
        {
            InitializeComponent();
            AtualizaLabels();
            var ports = SerialPort.GetPortNames();

            if (ports.Count() == 0)
            {
                MessageBox.Show("Nehuma porta serial disponível para conexão.", "Painel");
                return;
            }

            this.cmbSerialPorts.SelectedIndexChanged -= new System.EventHandler(this.cmbSerialPorts_SelectedIndexChanged);
            cmbSerialPorts.DataSource = ports;
            cmbSerialPorts.SelectedIndex = cmbSerialPorts.FindStringExact(Properties.Settings.Default["COM"].ToString());
            this.cmbSerialPorts.SelectedIndexChanged += new System.EventHandler(this.cmbSerialPorts_SelectedIndexChanged);

            Conecta(Properties.Settings.Default["COM"].ToString());
        }

        private void Conecta(string com)
        {
            port = new SerialPort();
            port.PortName = com;
            port.BaudRate = 9600;
            port.Parity = Parity.None;
            port.StopBits = StopBits.One;
            port.Handshake = Handshake.None;

            try
            {
                port.Open();
                port.ReadTimeout = 200;
                port.WriteTimeout = 500;
                if (!port.IsOpen)
                {
                    lblMensagens.Text = "Erro na conexão com a porta serial";
                }
                port.DataReceived += new SerialDataReceivedEventHandler(serialPort_DataReceived);
            }
            catch (Exception ex)
            {

                var erro = ex.Message;
                lblMensagens.Text = erro;
            }

        }


        private void serialPort_DataReceived(object sender, System.IO.Ports.SerialDataReceivedEventArgs e)
        {
            try
            {
                var text = port.ReadLine();
                SetText(text);
            }
            catch(Exception ex)
            {
                var erro  = ex.Message;
            }
        }

        private void SetText(string text)
        {
            if (this.lblMensagens.InvokeRequired)
            {
                SetTextCallback d = new SetTextCallback(SetText);
                this.Invoke(d, new object[] { text });
            }
            else
            {
                this.lblMensagens.Text = text;
                AtualizaInterface(text);
            }
        }


        private void AtualizaInterface(string comando)
        {
            if (comando.IndexOf("dataCB") >=0 )
            {
                //Install-Package System.Json -Version 4.0.20126.16343
                
                comando = comando.Replace("dataCB(", "");
                comando = comando.Replace(")", "");
                //JsonObject result = value as JsonObject;

                JsonValue value = JsonValue.Parse(comando);

                S1 = Convert.ToInt32(value["S1"].ToString()); 		
				S1HI = Convert.ToInt32(value["S1HI"].ToString()); 
                S1MI = Convert.ToInt32(value["S1MI"].ToString()); 
                S1HF = Convert.ToInt32(value["S1HF"].ToString()); 
                S1MF = Convert.ToInt32(value["S1MF"].ToString()); 
                TS1 = value["TS1"].ToString();
                S1P = Convert.ToInt32(value["S1P"].ToString()); 
				
				S2 = Convert.ToInt32(value["S2"].ToString());  		
				S2HI = Convert.ToInt32(value["S2HI"].ToString()); 
                S2MI = Convert.ToInt32(value["S2MI"].ToString()); 
                S2HF = Convert.ToInt32(value["S2HF"].ToString()); 
                S2MF = Convert.ToInt32(value["S2MF"].ToString()); 
                TS2 = value["TS2"].ToString();
                S2P = Convert.ToInt32(value["S2P"].ToString()); 

                				
				S3 = Convert.ToInt32(value["S3"].ToString());  		
				S3HI = Convert.ToInt32(value["S3HI"].ToString()); 
                S3MI = Convert.ToInt32(value["S3MI"].ToString()); 
                S3HF = Convert.ToInt32(value["S3HF"].ToString()); 
                S3MF = Convert.ToInt32(value["S3MF"].ToString()); 
                TS3 = value["TS3"].ToString();
                S3P = Convert.ToInt32(value["S3P"].ToString()); 
				
				S4 = Convert.ToInt32(value["S4"].ToString());  		
				S4HI = Convert.ToInt32(value["S4HI"].ToString()); 
                S4MI = Convert.ToInt32(value["S4MI"].ToString()); 
                S4HF = Convert.ToInt32(value["S4HF"].ToString()); 
                S4MF = Convert.ToInt32(value["S4MF"].ToString()); 
                TS4 = value["TS4"].ToString();
                S4P = Convert.ToInt32(value["S4P"].ToString()); 
				
				S5 = Convert.ToInt32(value["S5"].ToString());  		
				S5HI = Convert.ToInt32(value["S5HI"].ToString()); 
                S5MI = Convert.ToInt32(value["S5MI"].ToString()); 
                S5HF = Convert.ToInt32(value["S5HF"].ToString()); 
                S5MF = Convert.ToInt32(value["S5MF"].ToString()); 
                TS5 = value["TS5"].ToString();
                S5P = Convert.ToInt32(value["S5P"].ToString());


                /*

				S6 = Convert.ToInt32(value["S6"].ToString());  		
				S6HI = Convert.ToInt32(value["S6HI"].ToString()); 
                S6MI = Convert.ToInt32(value["S6MI"].ToString()); 
                S6HF = Convert.ToInt32(value["S6HF"].ToString()); 
                S6MF = Convert.ToInt32(value["S6MF"].ToString()); 
                TS6 = value["TS6"].ToString();
                S6P = Convert.ToInt32(value["S6P"].ToString());

                

				S7 = Convert.ToInt32(value["S7"].ToString());  		
				S7HI = Convert.ToInt32(value["S7HI"].ToString()); 
                S7MI = Convert.ToInt32(value["S7MI"].ToString()); 
                S7HF = Convert.ToInt32(value["S7HF"].ToString()); 
                S7MF = Convert.ToInt32(value["S7MF"].ToString()); 
                TS7 = value["TS7"].ToString();
                S7P = Convert.ToInt32(value["S7P"].ToString()); 
				
				S8 = Convert.ToInt32(value["S8"].ToString());  		
				S8HI = Convert.ToInt32(value["S8HI"].ToString()); 
                S8MI = Convert.ToInt32(value["S8MI"].ToString()); 
                S8HF = Convert.ToInt32(value["S8HF"].ToString()); 
                S8MF = Convert.ToInt32(value["S8MF"].ToString()); 
                TS8 = value["TS8"].ToString();
                S8P = Convert.ToInt32(value["S8P"].ToString()); 
            
                SRed = value["Red"].ToString(); 		
                SGreen = value["Green"].ToString(); 		
                SBlue = value["Blue"].ToString(); 		

    */
                Data = String.Format("{0}/{1}/{2}", value["Dia"].ToString(), value["Mes"].ToString(), value["Ano"].ToString());
                Hora = String.Format("{0}:{1}:{2}", value["Hora"].ToString(), value["Minuto"].ToString(), value["Segundo"].ToString());
                lblData.Text = Data + "  " + Hora;


                btnSaida1.Text = S1 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida1.BackColor = S1 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS1 == "A")
                {
                    lblTipo1.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S1HI, S1MI, S1HF, S1MF);
                } else if (TS1 == "P")
                {
                    lblTipo1.Text = String.Format("Pulso: {0} segundos", S1P);
                }
                else
                {
                    lblTipo1.Text = String.Format("Manual");
                }
				
				btnSaida2.Text = S2 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida2.BackColor = S2 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS2 == "A")
                {
                    lblTipo2.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S2HI, S2MI, S2HF, S2MF);
                } else if (TS2 == "P")
                {
                    lblTipo2.Text = String.Format("Pulso: {0} segundos", S2P);
                }
                else
                {
                    lblTipo2.Text = String.Format("Manual");
                }
				
				btnSaida3.Text = S3 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida3.BackColor = S3 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS3 == "A")
                {
                    lblTipo3.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S3HI, S3MI, S3HF, S3MF);
                } else if (TS3 == "P")
                {
                    lblTipo3.Text = String.Format("Pulso: {0} segundos", S3P);
                }
                else
                {
                    lblTipo3.Text = String.Format("Manual");
                }
				
				btnSaida4.Text = S4 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida4.BackColor = S4 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS4 == "A")
                {
                    lblTipo4.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S4HI, S4MI, S4HF, S4MF);
                } else if (TS4 == "P")
                {
                    lblTipo4.Text = String.Format("Pulso: {0} segundos", S4P);
                }
                else
                {
                    lblTipo4.Text = String.Format("Manual");
                }
				
				btnSaida5.Text = S5 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida5.BackColor = S5 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS5 == "A")
                {
                    lblTipo5.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S5HI, S5MI, S5HF, S5MF);
                } else if (TS5 == "P")
                {
                    lblTipo5.Text = String.Format("Pulso: {0} segundos", S5P);
                }
                else
                {
                    lblTipo5.Text = String.Format("Manual");
                }
				
				btnSaida6.Text = S6 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida6.BackColor = S6 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS6 == "A")
                {
                    lblTipo6.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S6HI, S6MI, S6HF, S6MF);
                } else if (TS6 == "P")
                {
                    lblTipo6.Text = String.Format("Pulso: {0} segundos", S6P);
                }
                else
                {
                    lblTipo6.Text = String.Format("Manual");
                }
				
				btnSaida7.Text = S7 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida7.BackColor = S7 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS7 == "A")
                {
                    lblTipo7.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S7HI, S7MI, S7HF, S7MF);
                } else if (TS7 == "P")
                {
                    lblTipo7.Text = String.Format("Pulso: {0} segundos", S7P);
                }
                else
                {
                    lblTipo7.Text = String.Format("Manual");
                }
				
				btnSaida8.Text = S8 == 0 ? "DESLIGADO" : "LIGADO";
                btnSaida8.BackColor = S8 == 0 ? Color.Crimson : Color.LimeGreen;
                if (TS8 == "A")
                {
                    lblTipo8.Text = String.Format("Agendado: {0}:{1} até {2}:{3}", S8HI, S8MI, S8HF, S8MF);
                } else if (TS8 == "P")
                {
                    lblTipo8.Text = String.Format("Pulso: {0} segundos", S8P);
                }
                else
                {
                    lblTipo8.Text = String.Format("Manual");
                }

            }

        }

        private void btnSaida1_Click(object sender, EventArgs e)
        {
            var novoStatus = S1 == 1 ? 0 : 1;
            EnviaComandoSaida(1, novoStatus);
        }

        private void btnSaida2_Click(object sender, EventArgs e)
        {
            var novoStatus = S2 == 1 ? 0 : 1;
            EnviaComandoSaida(2, novoStatus);
        }

        private void btnSaida3_Click(object sender, EventArgs e)
        {
            var novoStatus = S3 == 1 ? 0 : 1;
            EnviaComandoSaida(3, novoStatus);
        }

        private void btnSaida4_Click(object sender, EventArgs e)
        {
            var novoStatus = S4 == 1 ? 0 : 1;
            EnviaComandoSaida(4, novoStatus);
        }

        private void btnSaida5_Click(object sender, EventArgs e)
        {
            var novoStatus = S5 == 1 ? 0 : 1;
            EnviaComandoSaida(5, novoStatus);
        }

        private void btnSaida6_Click(object sender, EventArgs e)
        {
            var novoStatus = S6 == 1 ? 0 : 1;
            EnviaComandoSaida(6, novoStatus);
        }

        private void btnSaida7_Click(object sender, EventArgs e)
        {
            var novoStatus = S7 == 1 ? 0 : 1;
            EnviaComandoSaida(7, novoStatus);
        }

        private void btnSaida8_Click(object sender, EventArgs e)
        {
            var novoStatus = S8 == 1 ? 0 : 1;
            EnviaComandoSaida(8, novoStatus);
        }

        private void EnviaComandoSaida(int Saida, int Status)
        {
            var comando = string.Format("|D{0}{1}|", Saida, Status);
            EnviarComando(comando);
        } 

        public void EnviarComando(string comando)
        {
            if (port.IsOpen)
            {
                port.Write(comando);
            }
            else
            {
                lblMensagens.Text = "Não conectado com a placa, verifique antes de enviar os comandos!";
            }
        }

        private void rbManual_CheckedChanged(object sender, EventArgs e)
        {

        }

                   

        private void button1_Click(object sender, EventArgs e)
        {
            using (var frm = new HorarioPlaca())
            {
                frm.ShowDialog();
            }
        }

        private void btnTipo1_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 1;
                frm.Nome = lblSaida1.Text;
                frm.HI = S1HI;
                frm.MI = S1MI;
                frm.HF = S1HF;
                frm.MF = S1MF;
                frm.TS = TS1;
                frm.SP = S1P;

                frm.ShowDialog();
            }
        }

		private void btnTipo2_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 2;
                frm.Nome = lblSaida2.Text;
                frm.HI = S2HI;
                frm.MI = S2MI;
                frm.HF = S2HF;
                frm.MF = S2MF;
                frm.TS = TS2;
                frm.SP = S2P;

                frm.ShowDialog();
            }
        }

		private void btnTipo3_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 3;
                frm.Nome = lblSaida3.Text;
                frm.HI = S3HI;
                frm.MI = S3MI;
                frm.HF = S3HF;
                frm.MF = S3MF;
                frm.TS = TS3;
                frm.SP = S3P;

                frm.ShowDialog();
            }
        }

		private void btnTipo4_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 4;
                frm.Nome = lblSaida4.Text;
                frm.HI = S4HI;
                frm.MI = S4MI;
                frm.HF = S4HF;
                frm.MF = S4MF;
                frm.TS = TS4;
                frm.SP = S4P;

                frm.ShowDialog();
            }
        }

		private void btnTipo5_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 5;
                frm.Nome = lblSaida5.Text;
                frm.HI = S5HI;
                frm.MI = S5MI;
                frm.HF = S5HF;
                frm.MF = S5MF;
                frm.TS = TS5;
                frm.SP = S5P;

                frm.ShowDialog();
            }
        }

		private void btnTipo6_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 6;
                frm.Nome = lblSaida6.Text;
                frm.HI = S6HI;
                frm.MI = S6MI;
                frm.HF = S6HF;
                frm.MF = S6MF;
                frm.TS = TS6;
                frm.SP = S6P;

                frm.ShowDialog();
            }
        }

		private void btnTipo7_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 7;
                frm.Nome = lblSaida7.Text;
                frm.HI = S7HI;
                frm.MI = S7MI;
                frm.HF = S7HF;
                frm.MF = S7MF;
                frm.TS = TS7;
                frm.SP = S7P;

                frm.ShowDialog();
            }
        }

		private void btnTipo8_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 8;
                frm.Nome = lblSaida8.Text;
                frm.HI = S8HI;
                frm.MI = S8MI;
                frm.HF = S8HF;
                frm.MF = S8MF;
                frm.TS = TS8;
                frm.SP = S8P;

                frm.ShowDialog();
            }
        }

       

        private void cmbSerialPorts_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (port.IsOpen)
            {
                port.Close();
            }

            lblMensagens.Text = string.Empty;
            Conecta(cmbSerialPorts.Text);
            Properties.Settings.Default["COM"] = cmbSerialPorts.Text;
            Properties.Settings.Default.Save();
        }

        public void AtualizaLabels()
        {

            lblSaida1.Text = Properties.Settings.Default["Saida1"].ToString() == "" ? "Saída 1" : Properties.Settings.Default["Saida1"].ToString();
            lblSaida2.Text = Properties.Settings.Default["Saida2"].ToString() == "" ? "Saída 2" : Properties.Settings.Default["Saida2"].ToString();
            lblSaida3.Text = Properties.Settings.Default["Saida3"].ToString() == "" ? "Saída 3" : Properties.Settings.Default["Saida3"].ToString();
            lblSaida4.Text = Properties.Settings.Default["Saida4"].ToString() == "" ? "Saída 4" : Properties.Settings.Default["Saida4"].ToString();
            lblSaida5.Text = Properties.Settings.Default["Saida5"].ToString() == "" ? "Saída 5" : Properties.Settings.Default["Saida5"].ToString();
            lblSaida6.Text = Properties.Settings.Default["Saida6"].ToString() == "" ? "Saída 6" : Properties.Settings.Default["Saida6"].ToString();
            lblSaida7.Text = Properties.Settings.Default["Saida7"].ToString() == "" ? "Saída 7" : Properties.Settings.Default["Saida7"].ToString();
            lblSaida8.Text = Properties.Settings.Default["Saida8"].ToString() == "" ? "Saída 8" : Properties.Settings.Default["Saida8"].ToString();
        }

        private void tbR_Scroll(object sender, EventArgs e)
        {
            EnviaComandoRGB("6", tbR.Value);
        }

        private void trG_Scroll(object sender, EventArgs e)
        {
            EnviaComandoRGB("5", trG.Value);
        }

        private void trB_Scroll(object sender, EventArgs e)
        {
            EnviaComandoRGB("3", trB.Value);
        }

        private void EnviaComandoRGB(string Saida, int Status)
        {
            var comando = string.Format("|A{0}{1}|", Saida, Status);
            EnviarComando(comando);
        }
    }

}


