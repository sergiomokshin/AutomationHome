using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DesktopApplication
{
    public partial class ConfiguraSaida : Form
    {
        public int Saida { get; set; }
        public string Nome { get; set; }
        public string HrI { get; set;  }
        public string HrF { get; set; }


        public ConfiguraSaida()
        {
            InitializeComponent();            
        
        }
        

        private void btnAlterar_Click(object sender, EventArgs e)
        {

            Properties.Settings.Default["Saida" + Saida] = txtNomeSaida.Text;
            Properties.Settings.Default.Save();

            var comandoHrI = string.Format("|H{0}I{1}|", Saida, txtHI.Text);
            var comandoHrF = string.Format("|H{0}F{1}|", Saida, txtHF.Text);

            var formPainel = (System.Windows.Forms.Application.OpenForms["Painel"] as Painel);
            formPainel.EnviarComando(comandoHrI);
            System.Threading.Thread.Sleep(100);
            formPainel.EnviarComando(comandoHrF);
            System.Threading.Thread.Sleep(100);
            formPainel.AtualizaLabels();

            this.Close();
        }

        private void ConfiguraSaida_Load(object sender, EventArgs e)
        {
            txtNomeSaida.Text = Nome;
            txtHI.Text = HrI;
            txtHF.Text = HrF;
        }

        private void label6_Click(object sender, EventArgs e)
        {

        }
    }
}
