using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO.Ports;
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

        private string S1;
        private string S2;
        private string S3;
        private string S4;
        private string S5;
        private string S6;
        private string S7;
        private string S8;
        private string SR;
        private string SG;
        private string SB;
        private string ModoAgendado;

        private string S1HrI;
        private string S1HrF;
        private string S2HrI;
        private string S2HrF;
        private string S3HrI;
        private string S3HrF;
        private string S4HrI;
        private string S4HrF;
        private string S5HrI;
        private string S5HrF;
        private string S6HrI;
        private string S6HrF;
        private string S7HrI;
        private string S7HrF;
        private string S8HrI;
        private string S8HrF;
        // SRGBHrI =comandos[29];
        // SRGBHrF =comandos[30];

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
            catch
            {

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
            if (comando.IndexOf("|COMANDOS") == 0)
            {
                var comandos = comando.Split('#');
                S1 = comandos[1];
                S2 = comandos[2];
                S3 = comandos[3];
                S4 = comandos[4];
                S5 = comandos[5];
                S6 = comandos[6];
                S7 = comandos[7];
                S8 = comandos[8];
                SR = comandos[9];
                SG = comandos[10];
                SB = comandos[11];
                ModoAgendado = comandos[12];

                S1HrI = comandos[13];
                S1HrF = comandos[14];
                S2HrI = comandos[15];
                S2HrF = comandos[16];
                S3HrI = comandos[17];
                S3HrF = comandos[18];
                S4HrI = comandos[19];
                S4HrF = comandos[20];
                S5HrI = comandos[21];
                S5HrF = comandos[22];
                S6HrI = comandos[23];
                S6HrF = comandos[24];
                S7HrI = comandos[25];
                S7HrF = comandos[26];
                S8HrI = comandos[27];
                S8HrF = comandos[28];
                // SRGBHrI =comandos[29];
                // SRGBHrF =comandos[30];

                Data = comandos[31];
                Hora = comandos[32];

                rbAgendado.Checked = (ModoAgendado == "1");
                rbManual.Checked = (ModoAgendado == "0");
                lblData.Text = Data + "  " + Hora;

                btnSaida1.Text = S1 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida1.BackColor = S1 == "0" ? Color.Crimson : Color.LimeGreen;

                btnSaida2.Text = S2 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida2.BackColor = S2 == "0" ? Color.Crimson : Color.LimeGreen;

                btnSaida3.Text = S3 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida3.BackColor = S3 == "0" ? Color.Crimson : Color.LimeGreen;

                btnSaida4.Text = S4 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida4.BackColor = S4 == "0" ? Color.Crimson : Color.LimeGreen;

                btnSaida5.Text = S5 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida5.BackColor = S5 == "0" ? Color.Crimson : Color.LimeGreen;

                btnSaida6.Text = S6 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida6.BackColor = S6 == "0" ? Color.Crimson : Color.LimeGreen;

                btnSaida7.Text = S7 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida7.BackColor = S7 == "0" ? Color.Crimson : Color.LimeGreen;

                btnSaida8.Text = S8 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida8.BackColor = S8 == "0" ? Color.Crimson : Color.LimeGreen;



                lblAgendamento1.Text = String.Format("{0}:00 até {1}:59", S1HrI, S1HrF);
                lblAgendamento2.Text = String.Format("{0}:00 até {1}:59", S2HrI, S2HrF);
                lblAgendamento3.Text = String.Format("{0}:00 até {1}:59", S3HrI, S3HrF);
                lblAgendamento4.Text = String.Format("{0}:00 até {1}:59", S4HrI, S4HrF);
                lblAgendamento5.Text = String.Format("{0}:00 até {1}:59", S5HrI, S5HrF);
                lblAgendamento6.Text = String.Format("{0}:00 até {1}:59", S6HrI, S6HrF);
                lblAgendamento7.Text = String.Format("{0}:00 até {1}:59", S7HrI, S7HrF);
                lblAgendamento8.Text = String.Format("{0}:00 até {1}:59", S8HrI, S8HrF);
            }

        }

        private void btnSaida1_Click(object sender, EventArgs e)
        {
            var novoStatus = S1 == "1" ? 0 : 1;
            EnviaComandoSaida(1, novoStatus);
        }

        private void btnSaida2_Click(object sender, EventArgs e)
        {
            var novoStatus = S2 == "1" ? 0 : 1;
            EnviaComandoSaida(2, novoStatus);
        }

        private void btnSaida3_Click(object sender, EventArgs e)
        {
            var novoStatus = S3 == "1" ? 0 : 1;
            EnviaComandoSaida(3, novoStatus);
        }

        private void btnSaida4_Click(object sender, EventArgs e)
        {
            var novoStatus = S4 == "1" ? 0 : 1;
            EnviaComandoSaida(4, novoStatus);
        }

        private void btnSaida5_Click(object sender, EventArgs e)
        {
            var novoStatus = S5 == "1" ? 0 : 1;
            EnviaComandoSaida(5, novoStatus);
        }

        private void btnSaida6_Click(object sender, EventArgs e)
        {
            var novoStatus = S6 == "1" ? 0 : 1;
            EnviaComandoSaida(6, novoStatus);
        }

        private void btnSaida7_Click(object sender, EventArgs e)
        {
            var novoStatus = S7 == "1" ? 0 : 1;
            EnviaComandoSaida(7, novoStatus);
        }

        private void btnSaida8_Click(object sender, EventArgs e)
        {
            var novoStatus = S8 == "1" ? 0 : 1;
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

        private void rbAgendado_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void rbManual_Click(object sender, EventArgs e)
        {
            if (rbManual.Checked)
                EnviarComando("|M0|");
        }

        private void rbAgendado_Click(object sender, EventArgs e)
        {

            if (rbAgendado.Checked)
                EnviarComando("|M1|");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            using (var frm = new HorarioPlaca())
            {
                frm.ShowDialog();
            }
        }

        private void btnAge1_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 1;
                frm.Nome = lblSaida1.Text;
                frm.HrI = S1HrI;
                frm.HrF = S1HrF;
                frm.ShowDialog();
            }
        }

        private void btnAge2_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 2;
                frm.Nome = lblSaida2.Text;
                frm.HrI = S2HrI;
                frm.HrF = S2HrF;
                frm.ShowDialog();
            }
        }

        private void btnAge3_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 3;
                frm.Nome = lblSaida3.Text;
                frm.HrI = S3HrI;
                frm.HrF = S3HrF;
                frm.ShowDialog();
            }
        }

        private void btnAge4_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 4;
                frm.Nome = lblSaida1.Text;
                frm.HrI = S1HrI;
                frm.HrF = S1HrF;
                frm.ShowDialog();
            }
        }

        private void btnAge5_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 5;
                frm.Nome = lblSaida1.Text;
                frm.HrI = S1HrI;
                frm.HrF = S1HrF;
                frm.ShowDialog();
            }
        }

        private void btnAge6_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 6;
                frm.Nome = lblSaida6.Text;
                frm.HrI = S6HrI;
                frm.HrF = S6HrF;
                frm.ShowDialog();
            }
        }

        private void btnAge7_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 7;
                frm.Nome = lblSaida7.Text;
                frm.HrI = S7HrI;
                frm.HrF = S7HrF;
                frm.ShowDialog();
            }
        }

        private void btnAge8_Click(object sender, EventArgs e)
        {
            using (var frm = new ConfiguraSaida())
            {
                frm.Saida = 8;
                frm.Nome = lblSaida8.Text;
                frm.HrI = S8HrI;
                frm.HrF = S8HrF;
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
            EnviaComandoRGB("6", trB.Value);
        }

        private void trG_Scroll(object sender, EventArgs e)
        {
            EnviaComandoRGB("5", trB.Value);
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


