using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using SQLite;

namespace sqlite
{
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public class Productos
        {
            [PrimaryKey]
            [AutoIncrement]
            public int Id { get; set; }
            public string Nombre { get; set; }
            public double PreciodeVenta { get; set; }
            public int Cantidad { get; set; }
            public double PreciodeCosto { get; set; }
            public string Descripcion { get; set; }
            public string Foto { get; set; }
        }

        public void OpenDatabase()
        {
            string folder = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
            string rutaDb = Path.Combine(folder, "MiNegocio1.db");

            var db = new SQLiteConnection(rutaDb);
            db.CreateTable<Productos>();
            var todoslosproductos = db.Table<Productos>().ToList();

            lst.ItemsSource = null;
            lst.ItemsSource = todoslosproductos;
            db.Close();
        }

        public MainPage()
        {
            InitializeComponent();

            lst.IsRefreshing = true;
            OpenDatabase();
            lst.IsRefreshing = false;

            Appearing += MainPage_Appearing;
        }

        private void MainPage_Appearing(object sender, EventArgs e)
        {
            OpenDatabase();
        }

        private void Lst_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            var elemento = e.Item as Productos;
            var detailPage = new DetalleProducto
            {
                BindingContext = elemento
            };
            Navigation.PushAsync(detailPage);
        }

        private async void MenuItem1_Clicked(object sender, EventArgs e)
        {
            var detailPage = new AgregarProduct();
            await Navigation.PushAsync(detailPage);
            //OpenDatabase();
        }
    }
}
