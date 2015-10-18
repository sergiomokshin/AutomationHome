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


        public Painel()
        {
            InitializeComponent();
            var ports = SerialPort.GetPortNames();

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
                port.WriteTimeout = 500;//
                if (!port.IsOpen)
                {
                    lblMensagens.Text = "Erro na conexão com a porta serial";
                }
                port.DataReceived += new SerialDataReceivedEventHandler(serialPort_DataReceived);
            }
            catch (Exception ex){

                var erro = ex.Message;
                lblMensagens.Text = erro;
            }

        }

        private void serialPort_DataReceived(object sender, System.IO.Ports.SerialDataReceivedEventArgs e)
        {
            try {
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
            if(comando.IndexOf("|COMANDOS") == 0)
            {
                var comandos = comando.Split('#');
                var S1 = comandos[1];
                var S2 = comandos[2];
                var S3 = comandos[3];
                var S4 = comandos[4];
                var S5 = comandos[5];
                var S6 = comandos[6];
                var S7 = comandos[7];
                var S8 = comandos[8];
                var SR = comandos[9];
                var SG = comandos[10];
                var SB = comandos[11];
                var ModoAgendado = comandos[12];

                var S1HrI =comandos[13];
                var S1HrF =comandos[14];
                var S2HrI =comandos[15];
                var S2HrF =comandos[16];
                var S3HrI =comandos[17];
                var S3HrF =comandos[18];
                var S4HrI =comandos[19];
                var S4HrF =comandos[20];
                var S5HrI =comandos[21];
                var S5HrF =comandos[22];
                var S6HrI =comandos[23];
                var S6HrF =comandos[24];
                var S7HrI =comandos[25];
                var S7HrF =comandos[26];
                var S8HrI =comandos[27];
                var S8HrF =comandos[28];
                // SRGBHrI =comandos[29];
                // SRGBHrF =comandos[30];

                var Data =comandos[31];
                var Hora =comandos[32];

                rbAgendado.Checked = (ModoAgendado == "1");
                rbManual.Checked = (ModoAgendado == "0");
                lblData.Text = Data + "  " + Hora;

                btnSaida1.Text = S1 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida2.Text = S2 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida3.Text = S3 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida4.Text = S4 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida5.Text = S5 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida6.Text = S6 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida7.Text = S7 == "0" ? "DESLIGADO" : "LIGADO";
                btnSaida8.Text = S8 == "0" ? "DESLIGADO" : "LIGADO";

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
            var novoStatus = btnSaida1.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(1, novoStatus);
        }

        private void btnSaida2_Click(object sender, EventArgs e)
        {
            var novoStatus = btnSaida2.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(2, novoStatus);
        }

        private void btnSaida3_Click(object sender, EventArgs e)
        {
            var novoStatus = btnSaida3.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(3, novoStatus);
        }

        private void btnSaida4_Click(object sender, EventArgs e)
        {
            var novoStatus = btnSaida4.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(4, novoStatus);
        }

        private void btnSaida5_Click(object sender, EventArgs e)
        {
            var novoStatus = btnSaida5.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(5, novoStatus);
        }

        private void btnSaida6_Click(object sender, EventArgs e)
        {
            var novoStatus = btnSaida6.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(6, novoStatus);
        }

        private void btnSaida7_Click(object sender, EventArgs e)
        {
            var novoStatus = btnSaida7.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(7, novoStatus);
        }

        private void btnSaida8_Click(object sender, EventArgs e)
        {
            var novoStatus = btnSaida8.Text == "LIGADO" ? 0 : 1;
            EnviaComandoSaida(8, novoStatus);
        }

        private void EnviaComandoSaida(int Saida, int Status)
        {
            var comando = string.Format("|D{0}{1}|", Saida, Status);
            EnviarComando(comando);
        }

        private void EnviarComando(string comando)
        {
            if(port.IsOpen)
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
            if(rbManual.Checked)
                EnviarComando("|M0|");
        }

        private void rbAgendado_Click(object sender, EventArgs e)
        {

            if (rbAgendado.Checked)
                EnviarComando("|M1|");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            HorarioPlaca frmHorario = new HorarioPlaca();
            frmHorario.Show();
        }

        private void btnAge1_Click(object sender, EventArgs e)
        {
            ConfiguraSaida frmConfigura = new ConfiguraSaida();
            frmConfigura.Show();
        }

        private void btnAge2_Click(object sender, EventArgs e)
        {

        }

        private void btnAge3_Click(object sender, EventArgs e)
        {

        }

        private void btnAge4_Click(object sender, EventArgs e)
        {

        }

        private void btnAge5_Click(object sender, EventArgs e)
        {

        }

        private void btnAge6_Click(object sender, EventArgs e)
        {

        }

        private void btnAge7_Click(object sender, EventArgs e)
        {

        }

        private void btnAge8_Click(object sender, EventArgs e)
        {

        }



        private void cmbSerialPorts_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(port.IsOpen)
            {
                port.Close();
            }

            lblMensagens.Text = string.Empty;
            Conecta(cmbSerialPorts.Text);
            Properties.Settings.Default["COM"] = cmbSerialPorts.Text;
            Properties.Settings.Default.Save();
        }
    }
    
}


