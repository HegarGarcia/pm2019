using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Internals;

namespace list_view
{
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public class Fruta
        {
            public string Nombre { get; set; }
            public string Url { get; set; }
        }

        private ObservableCollection<Fruta> datos = new ObservableCollection<Fruta>();
        public MainPage()
        {
            InitializeComponent();
            lista.ItemsSource = datos;
        }

        public void Button_Clicked(object sender, EventArgs e)
        {
            datos.Add(new Fruta { Nombre = fruta_nombre.Text, Url = fruta_url.Text });
            Console.WriteLine(datos[0].ToString());
        }

        private void MenuItem_Clicked(object sender, EventArgs e)
        {
            DisplayAlert("Mensaje", "Seleccion Mostrar", "ok");
        }

        private void MenuItem_Borrar(object sender, EventArgs e)
        {
            var mi = ((MenuItem)sender);

            DisplayAlert("Fruta seleccionada", "Fruta:" + mi.CommandParameter.ToString(), "Ok");
        }

        async private void Milista_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            var myListView = (ListView)sender;
            var myItem = myListView.SelectedItem;
            int index = datos.IndexOf(myItem);

            string action = await DisplayActionSheet("Acciones:", "Cancelar", null, "Eliminar", "Editar");

            if (action == "Eliminar")
            {
                datos.RemoveAt(index);
                await DisplayAlert("Eliminar elemento", "Se elimino el lemento no:" + index, "OK");
                lista.ItemsSource = null;
                lista.ItemsSource = datos;
            }

            if (action == "Editar")
            {
                await DisplayAlert("Mensaje", "Seleccio editar", "ok");
            }
        }
    }
}
