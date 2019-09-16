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
    public partial class DetalleProducto : ContentPage
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

        async private void EliminaProducto(object sender, EventArgs e)
        {
            string folder = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
            string rutaDb = Path.Combine(folder, "MiNegocio1.db");
            var db = new SQLiteConnection(rutaDb);

            int MiId = int.Parse(Id.Text);

            var respuesta = await DisplayAlert("Alerta!!", "¿Esta seguro de que lo desea eliminar?", "Si", "No");

            if (respuesta == true)
            {
                db.Delete<Productos>(MiId);
                await Application.Current.MainPage.Navigation.PopAsync();
            }

            db.Close();
        }
        private void ActualizarRegistro(object sender, EventArgs e)
        {
            string folder = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
            string rutaDb = Path.Combine(folder, "MiNegocio1.db");
            var db = new SQLiteConnection(rutaDb);

            int MiId = int.Parse(Id.Text);
            //DisplayAlert("ID", "" + MiId, "Ok");

            var registro = new Productos
            {
                Id = MiId,
                Nombre = nombre.Text,
                PreciodeCosto = double.Parse(preciodecosto.Text),
                Cantidad = int.Parse(cantidad.Text),
                PreciodeVenta = double.Parse(preciodeventa.Text),
                Descripcion = descipcion.Text,
                Foto = foto.Text
            };

            db.Table<Productos>();

            db.Update(registro);
            db.Close();

            Application.Current.MainPage.Navigation.PopAsync();
        }


        public DetalleProducto()
        {
            InitializeComponent();
        }
    }
}
