namespace DesktopApplication
{
    partial class ConfiguraSaida
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ConfiguraSaida));
            this.label1 = new System.Windows.Forms.Label();
            this.txtNomeSaida = new System.Windows.Forms.TextBox();
            this.Agendamento = new System.Windows.Forms.Label();
            this.txtHrI = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.txtHrF = new System.Windows.Forms.TextBox();
            this.btnAlterar = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(50, 28);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(36, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Saída";
            // 
            // txtNomeSaida
            // 
            this.txtNomeSaida.Location = new System.Drawing.Point(92, 25);
            this.txtNomeSaida.Name = "txtNomeSaida";
            this.txtNomeSaida.Size = new System.Drawing.Size(162, 20);
            this.txtNomeSaida.TabIndex = 1;
            // 
            // Agendamento
            // 
            this.Agendamento.AutoSize = true;
            this.Agendamento.Location = new System.Drawing.Point(13, 57);
            this.Agendamento.Name = "Agendamento";
            this.Agendamento.Size = new System.Drawing.Size(73, 13);
            this.Agendamento.TabIndex = 2;
            this.Agendamento.Text = "Agendamento";
            // 
            // txtHrI
            // 
            this.txtHrI.Location = new System.Drawing.Point(92, 54);
            this.txtHrI.Name = "txtHrI";
            this.txtHrI.Size = new System.Drawing.Size(31, 20);
            this.txtHrI.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(126, 58);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(22, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = ":00";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(154, 58);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(22, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "até";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(214, 58);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(22, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = ":59";
            // 
            // txtHrF
            // 
            this.txtHrF.Location = new System.Drawing.Point(182, 54);
            this.txtHrF.Name = "txtHrF";
            this.txtHrF.Size = new System.Drawing.Size(31, 20);
            this.txtHrF.TabIndex = 6;
            // 
            // btnAlterar
            // 
            this.btnAlterar.Location = new System.Drawing.Point(16, 90);
            this.btnAlterar.Name = "btnAlterar";
            this.btnAlterar.Size = new System.Drawing.Size(238, 23);
            this.btnAlterar.TabIndex = 8;
            this.btnAlterar.Text = "Alterar";
            this.btnAlterar.UseVisualStyleBackColor = true;
            this.btnAlterar.Click += new System.EventHandler(this.btnAlterar_Click);
            // 
            // ConfiguraSaida
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(273, 154);
            this.Controls.Add(this.btnAlterar);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txtHrF);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.txtHrI);
            this.Controls.Add(this.Agendamento);
            this.Controls.Add(this.txtNomeSaida);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "ConfiguraSaida";
            this.Text = "Configurar Saída";
            this.Load += new System.EventHandler(this.ConfiguraSaida_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txtNomeSaida;
        private System.Windows.Forms.Label Agendamento;
        private System.Windows.Forms.TextBox txtHrI;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtHrF;
        private System.Windows.Forms.Button btnAlterar;
    }
}