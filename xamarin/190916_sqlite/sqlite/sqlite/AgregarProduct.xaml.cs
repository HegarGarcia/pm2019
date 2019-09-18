using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using SQLite;

namespace sqlite
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AgregarProduct : ContentPage
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

        private void AgregarProductos()
        {
            string folder = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
            string rutaDb = Path.Combine(folder, "MiNegocio1.db");
            
            var db = new SQLiteConnection(rutaDb);
            db.CreateTable<Productos>();

            var registro = new Productos
            {
                Nombre = nombre.Text,
                PreciodeCosto = double.Parse(preciodecosto.Text),
                Cantidad = int.Parse(cantidad.Text),
                PreciodeVenta = double.Parse(preciodeventa.Text),
                Descripcion = descipcion.Text,
                Foto = foto.Text
            };

            db.Insert(registro);
            db.Close();
            DisplayAlert("Agregar", "El registro fue agregado con exito!", "ok");
        }

        private void MenuItem1_Clicked(object sender, EventArgs e)
        {
            AgregarProductos();
            Application.Current.MainPage.Navigation.PopAsync();
        }

        public AgregarProduct()
        {
            InitializeComponent();
        }
    }
}