using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using System.Net;
using System.Net.Http;
using Newtonsoft.Json;

namespace mysql
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public class User
        {
            public int id { get; set; }
            public string name { get; set; }
            public string user { get; set; }
            public string password { get; set; }
        }

        public List<User> users = new List<User>();
        public HttpClient client = new HttpClient();
        public const string getAllUsersUrl = "https://orthotone-officers.000webhostapp.com/get_users.php";

        public MainPage()
        {
            InitializeComponent();

            userList.ItemsSource = users;
            client.MaxResponseContentBufferSize = 256000;
            LoadButtonClick(null, null);
        }

        private async void RegiterButttonClick(object sender, EventArgs e)
        {
            await RegisterUser();
        }

        private async void LoadButtonClick(object sender, EventArgs e)
        {
            userList.BeginRefresh();
            await ListUsers();
            userList.ItemsSource = null;
            userList.ItemsSource = users;
            userList.EndRefresh();
        }

        private async Task RegisterUser()
        {
            var fullname = WebUtility.UrlEncode(fullnameEntry.Text);
            var username = WebUtility.UrlEncode(usernameEntry.Text);
            var password = WebUtility.UrlEncode(passwordEntry.Text);

            string queryUrl = $"https://orthotone-officers.000webhostapp.com/add_user.php?name={fullname}&user={username}&password={password}";
            var uri = new Uri(string.Format(queryUrl, string.Empty));

            var response = await client.GetAsync(uri);

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                string msg = content.ToString();
                await DisplayAlert("Se agregó el registro!", msg, "OK");
            }
        }

        private async Task ListUsers()
        {
            var uri = new Uri(string.Format(getAllUsersUrl, string.Empty));
            var response = await client.GetAsync(uri);

            if (!response.IsSuccessStatusCode)
            {
                return;
            }

            var content = await response.Content.ReadAsStringAsync();
            users = JsonConvert.DeserializeObject<List<User>>(content);

            await DisplayAlert("Carga completa", $"Se cargaron {users.Count}", "OK");
        }

        private async void UserList_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            User user = e.Item as User;

            await DisplayAlert("Usuario", $"Nombre {user.name}\n Contraseña: {user.password}", "OK");
        }
    }
}
