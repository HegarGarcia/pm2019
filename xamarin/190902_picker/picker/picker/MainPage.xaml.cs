using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace picker
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
            float num_a, num_b;
            string result = "", option;

            try
            {
                num_a = float.Parse(valor_1.Text);
                num_b = float.Parse(valor_2.Text);
                option = operations.SelectedItem.ToString().ToLower();
            }
            catch
            {
                result_label.Text = "No hay valores o no son números";
                return;
            }


            switch (option)
            {
                case "suma":
                    {
                        result = $"{num_a} + {num_b} = {num_a + num_b}";
                        break;
                    }
                case "resta":
                    {
                        result = $"{num_a} - {num_b} = {num_a - num_b}";
                        break;
                    }
                case "multiplicación":
                    {
                        result = $"{num_a} x {num_b} = {num_a * num_b}";
                        break;
                    }
                case "división":
                    {
                        result = $"{num_a} / {num_b} = {num_a / num_b}";
                        break;
                    }
            }

            result_label.Text = result;
        }
    }
}
