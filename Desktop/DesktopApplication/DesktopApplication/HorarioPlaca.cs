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
    public partial class HorarioPlaca : Form
    {

        public HorarioPlaca()
        {
            InitializeComponent();

            datePicker.Format = DateTimePickerFormat.Custom;
            datePicker.CustomFormat = "dd/MM/yyyy     HH:mm:ss";
        }

        private void btnAlterar_Click(object sender, EventArgs e)
        {
            var comando = string.Format("Ty{0}yz{1}z|", datePicker.Value.Date.ToString("dd/MM/yy"), datePicker.Value.ToString("HH:mm"));
            if (System.Windows.Forms.Application.OpenForms["Painel"] != null)
            {
                (System.Windows.Forms.Application.OpenForms["Painel"] as Painel).EnviarComando(comando);
            }
            this.Close();

        }

        private void datePicker_ValueChanged(object sender, EventArgs e)
        {


        }
    }
}
