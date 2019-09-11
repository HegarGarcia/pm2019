using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace suma
{
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void Button_Clicked(object sender, EventArgs e)
        {
            float primerNumero = float.Parse(valor1.Text);
            float segudoNumero = float.Parse(valor2.Text);
            float resultado = primerNumero + segudoNumero;
            this.resultado.Text = primerNumero + "+" + segudoNumero + "=" + resultado;
        }
    }
}
