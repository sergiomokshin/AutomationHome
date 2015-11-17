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
            this.btnAlterar = new System.Windows.Forms.Button();
            this.gbDados = new System.Windows.Forms.GroupBox();
            this.cboTipo = new System.Windows.Forms.ComboBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txtNomeSaida = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.gbAgendamento = new System.Windows.Forms.GroupBox();
            this.txtMF = new System.Windows.Forms.TextBox();
            this.txtMI = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txtHF = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.txtHI = new System.Windows.Forms.TextBox();
            this.Agendamento = new System.Windows.Forms.Label();
            this.gbPulso = new System.Windows.Forms.GroupBox();
            this.label7 = new System.Windows.Forms.Label();
            this.txtPulso = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.gbDados.SuspendLayout();
            this.gbAgendamento.SuspendLayout();
            this.gbPulso.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnAlterar
            // 
            this.btnAlterar.Location = new System.Drawing.Point(64, 238);
            this.btnAlterar.Name = "btnAlterar";
            this.btnAlterar.Size = new System.Drawing.Size(238, 31);
            this.btnAlterar.TabIndex = 8;
            this.btnAlterar.Text = "Alterar";
            this.btnAlterar.UseVisualStyleBackColor = true;
            this.btnAlterar.Click += new System.EventHandler(this.btnAlterar_Click);
            // 
            // gbDados
            // 
            this.gbDados.Controls.Add(this.cboTipo);
            this.gbDados.Controls.Add(this.label5);
            this.gbDados.Controls.Add(this.txtNomeSaida);
            this.gbDados.Controls.Add(this.label1);
            this.gbDados.Location = new System.Drawing.Point(12, 12);
            this.gbDados.Name = "gbDados";
            this.gbDados.Size = new System.Drawing.Size(349, 100);
            this.gbDados.TabIndex = 16;
            this.gbDados.TabStop = false;
            // 
            // cboTipo
            // 
            this.cboTipo.FormattingEnabled = true;
            this.cboTipo.Location = new System.Drawing.Point(38, 59);
            this.cboTipo.Name = "cboTipo";
            this.cboTipo.Size = new System.Drawing.Size(162, 21);
            this.cboTipo.TabIndex = 14;
            this.cboTipo.SelectedIndexChanged += new System.EventHandler(this.cboTipo_SelectedIndexChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(5, 62);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(28, 13);
            this.label5.TabIndex = 13;
            this.label5.Text = "Tipo";
            // 
            // txtNomeSaida
            // 
            this.txtNomeSaida.Location = new System.Drawing.Point(38, 20);
            this.txtNomeSaida.Name = "txtNomeSaida";
            this.txtNomeSaida.Size = new System.Drawing.Size(162, 20);
            this.txtNomeSaida.TabIndex = 12;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(0, 23);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(36, 13);
            this.label1.TabIndex = 11;
            this.label1.Text = "Saída";
            // 
            // gbAgendamento
            // 
            this.gbAgendamento.Controls.Add(this.txtMF);
            this.gbAgendamento.Controls.Add(this.txtMI);
            this.gbAgendamento.Controls.Add(this.label4);
            this.gbAgendamento.Controls.Add(this.txtHF);
            this.gbAgendamento.Controls.Add(this.label3);
            this.gbAgendamento.Controls.Add(this.label2);
            this.gbAgendamento.Controls.Add(this.txtHI);
            this.gbAgendamento.Controls.Add(this.Agendamento);
            this.gbAgendamento.Location = new System.Drawing.Point(12, 111);
            this.gbAgendamento.Name = "gbAgendamento";
            this.gbAgendamento.Size = new System.Drawing.Size(349, 64);
            this.gbAgendamento.TabIndex = 17;
            this.gbAgendamento.TabStop = false;
            // 
            // txtMF
            // 
            this.txtMF.Location = new System.Drawing.Point(245, 19);
            this.txtMF.Name = "txtMF";
            this.txtMF.Size = new System.Drawing.Size(31, 20);
            this.txtMF.TabIndex = 20;
            // 
            // txtMI
            // 
            this.txtMI.Location = new System.Drawing.Point(132, 19);
            this.txtMI.Name = "txtMI";
            this.txtMI.Size = new System.Drawing.Size(31, 20);
            this.txtMI.TabIndex = 19;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(229, 22);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(10, 13);
            this.label4.TabIndex = 18;
            this.label4.Text = ":";
            // 
            // txtHF
            // 
            this.txtHF.Location = new System.Drawing.Point(197, 18);
            this.txtHF.Name = "txtHF";
            this.txtHF.Size = new System.Drawing.Size(31, 20);
            this.txtHF.TabIndex = 17;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(169, 22);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(22, 13);
            this.label3.TabIndex = 16;
            this.label3.Text = "até";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(123, 22);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(10, 13);
            this.label2.TabIndex = 15;
            this.label2.Text = ":";
            // 
            // txtHI
            // 
            this.txtHI.Location = new System.Drawing.Point(89, 18);
            this.txtHI.Name = "txtHI";
            this.txtHI.Size = new System.Drawing.Size(31, 20);
            this.txtHI.TabIndex = 14;
            // 
            // Agendamento
            // 
            this.Agendamento.AutoSize = true;
            this.Agendamento.Location = new System.Drawing.Point(10, 25);
            this.Agendamento.Name = "Agendamento";
            this.Agendamento.Size = new System.Drawing.Size(73, 13);
            this.Agendamento.TabIndex = 13;
            this.Agendamento.Text = "Agendamento";
            // 
            // gbPulso
            // 
            this.gbPulso.Controls.Add(this.label7);
            this.gbPulso.Controls.Add(this.txtPulso);
            this.gbPulso.Controls.Add(this.label6);
            this.gbPulso.Location = new System.Drawing.Point(12, 175);
            this.gbPulso.Name = "gbPulso";
            this.gbPulso.Size = new System.Drawing.Size(349, 45);
            this.gbPulso.TabIndex = 18;
            this.gbPulso.TabStop = false;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(86, 18);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(71, 13);
            this.label7.TabIndex = 18;
            this.label7.Text = "Em segundos";
            // 
            // txtPulso
            // 
            this.txtPulso.Location = new System.Drawing.Point(52, 14);
            this.txtPulso.Name = "txtPulso";
            this.txtPulso.Size = new System.Drawing.Size(31, 20);
            this.txtPulso.TabIndex = 17;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(10, 17);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(36, 13);
            this.label6.TabIndex = 16;
            this.label6.Text = "Pulso:";
            // 
            // ConfiguraSaida
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(373, 281);
            this.Controls.Add(this.gbPulso);
            this.Controls.Add(this.gbAgendamento);
            this.Controls.Add(this.gbDados);
            this.Controls.Add(this.btnAlterar);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "ConfiguraSaida";
            this.Text = "Configurar Saída";
            this.Load += new System.EventHandler(this.ConfiguraSaida_Load);
            this.gbDados.ResumeLayout(false);
            this.gbDados.PerformLayout();
            this.gbAgendamento.ResumeLayout(false);
            this.gbAgendamento.PerformLayout();
            this.gbPulso.ResumeLayout(false);
            this.gbPulso.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Button btnAlterar;
        private System.Windows.Forms.GroupBox gbDados;
        private System.Windows.Forms.ComboBox cboTipo;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txtNomeSaida;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox gbAgendamento;
        private System.Windows.Forms.TextBox txtMF;
        private System.Windows.Forms.TextBox txtMI;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtHF;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtHI;
        private System.Windows.Forms.Label Agendamento;
        private System.Windows.Forms.GroupBox gbPulso;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox txtPulso;
        private System.Windows.Forms.Label label6;
    }
}