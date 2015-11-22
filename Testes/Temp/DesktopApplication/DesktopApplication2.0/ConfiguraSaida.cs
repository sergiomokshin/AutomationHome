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
        public int HI { get; set; }
        public int MI { get; set; }
        public int HF { get; set; }
        public int MF { get; set; }
        public string TS { get; set; }
        public int SP { get; set; }


        public ConfiguraSaida()
        {
            InitializeComponent();

        }

        private void ConfiguraSaida_Load(object sender, EventArgs e)
        {

            txtNomeSaida.Text = Nome;

            cboTipo.DisplayMember = "Nome";
            cboTipo.ValueMember = "Tipo";

            cboTipo.Items.Add(new TipoItem { Nome = "Manual", Tipo = "M" });
            cboTipo.Items.Add(new TipoItem { Nome = "Agendado", Tipo = "A" });
            cboTipo.Items.Add(new TipoItem { Nome = "Pulso", Tipo = "P" });

            if (TS == String.Empty)
                TS = "M";

            if(TS == "M")
            {
                cboTipo.SelectedIndex = 0;
            }else if (TS == "A")
            {
                cboTipo.SelectedIndex = 1;
            }
            else if (TS == "P")
            {
                cboTipo.SelectedIndex = 2;
            }



            gbAgendamento.Enabled = (TS == "A");
            gbPulso.Enabled = (TS == "P");

            txtHI.Text = HI.ToString();
            txtMI.Text = MI.ToString();
            txtHF.Text = HF.ToString();
            txtMF.Text = MF.ToString();

            txtPulso.Text = SP.ToString();

        }

        private void btnAlterar_Click(object sender, EventArgs e)
        {
            Cursor.Current = Cursors.WaitCursor;
            try
            {
                Properties.Settings.Default["Saida" + Saida] = txtNomeSaida.Text;
                Properties.Settings.Default.Save();

                var comandoHI = string.Format("|H{0}I{1}|", Saida, txtHI.Text);
                var comandoHF = string.Format("|H{0}F{1}|", Saida, txtHF.Text);

                var comandoMI = string.Format("|M{0}I{1}|", Saida, txtHI.Text);
                var comandoMF = string.Format("|M{0}F{1}|", Saida, txtHF.Text);

                var comandoTipo = string.Format("|T{0}{1}|", Saida, ((TipoItem) cboTipo.SelectedItem).Tipo);
                var comandoPulso = string.Format("|B{0}{1}|", Saida, txtPulso.Text);

                var formPainel = (System.Windows.Forms.Application.OpenForms["Painel"] as Painel);
                formPainel.EnviarComando(comandoHI);
                System.Threading.Thread.Sleep(100);
                formPainel.EnviarComando(comandoMI);
                System.Threading.Thread.Sleep(100);
                formPainel.EnviarComando(comandoHF);
                System.Threading.Thread.Sleep(100);
                formPainel.EnviarComando(comandoMF);
                System.Threading.Thread.Sleep(100);

                formPainel.EnviarComando(comandoTipo);
                System.Threading.Thread.Sleep(100);

                formPainel.EnviarComando(comandoPulso);
                System.Threading.Thread.Sleep(100);

                formPainel.AtualizaLabels();
            }
            catch (Exception ex)
            {
                Cursor.Current = Cursors.Default;
                MessageBox.Show(ex.Message);
            }

            Cursor.Current = Cursors.Default;
            this.Close();
        }



        private void label6_Click(object sender, EventArgs e)
        {


        }

        private void cboTipo_SelectedIndexChanged(object sender, EventArgs e)
        {
            gbAgendamento.Enabled = ((TipoItem)cboTipo.SelectedItem).Tipo == "A";
            gbPulso.Enabled = ((TipoItem)cboTipo.SelectedItem).Tipo == "P";
        }
    }

    public class TipoItem
    {
        public string Tipo { get; set; }
        public string Nome { get; set; }

    }

}
