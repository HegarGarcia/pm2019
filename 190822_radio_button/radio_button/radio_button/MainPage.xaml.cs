using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace radio_button
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }


        private void Button_Clicked(object sender, EventArgs e)
        {
            
            if (String.IsNullOrEmpty(valor1.Text) || String.IsNullOrEmpty(valor2.Text))
            {
                resultado.Text = "No hay valores";
                return;
            }

            float num_a = float.Parse(valor1.Text);
            float num_b = float.Parse(valor2.Text);
            string result = "";

            switch (operacion.SelectedIndex)
            {
                case -1:
                    {
                        result = "No seleccionó ninguna operacion";
                        break;
                    }
                case 0:
                    {
                        result = $"{num_a} + {num_b} = {num_a + num_b}";
                        break;
                    }
                case 1:
                    {
                        result = $"{num_a} - {num_b} = {num_a - num_b}";
                        break;
                    }
                case 2:
                    {
                        result = $"{num_a} x {num_b} = {num_a * num_b}";
                        break;
                    }
                case 3:
                    {
                        result = $"{num_a} / {num_b} = {num_a / num_b}";
                        break;
                    }
            }

            resultado.Text = result;
        }
    }
}
