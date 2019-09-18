using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

using System.Net.Http;
using Newtonsoft.Json;

namespace Authentication
{
    [DesignTimeVisible(false)]
    public partial class LoginPage : ContentPage
    {

        private List<User> users = new List<User>();
        private HttpClient client = new HttpClient();
        public LoginPage()
        {
            InitializeComponent();
            client.MaxResponseContentBufferSize = 256000;

            var hasUser = Application.Current.Properties.ContainsKey("user");
            var hasPassword = Application.Current.Properties.ContainsKey("password");

            if (hasUser)
            {
                userEntry.Text = Application.Current.Properties["user"].ToString();
            }

            if (hasPassword)
            {
                passwordEntry.Text = Application.Current.Properties["password"].ToString();
            }

            if (hasPassword && hasUser)
            {
                rememberToggle.IsEnabled = true;
            }
        }

        private async void Login_User_Click(object sender, EventArgs e)
        {
            await Login_User();
        }

        private async Task Login_User()
        {
            string userString = userEntry.Text;
            string passwordString = passwordEntry.Text;

            string queryUrl = String.Format("https://orthotone-officers.000webhostapp.com/authenticate.php?user={0}&password={1}", userString, passwordString);
            var uri = new Uri(String.Format(queryUrl, string.Empty));
            var response = await client.GetAsync(uri);

            if (!response.IsSuccessStatusCode)
            {
                return;
            }

            var content = await response.Content.ReadAsStringAsync();

            if (content.Length <= 0)
            {
                await DisplayAlert("Autenticación ", "Lo sentimos no estas registrado", "OK");
                return;
            }

            users = JsonConvert.DeserializeObject<List<User>>(content);
            User currentUser = null;
            users.ForEach(delegate (User user)
            {
                currentUser = user;
            });

            if (rememberToggle.IsEnabled)
            {
                if (Application.Current.Properties.ContainsKey("user"))
                {
                    Application.Current.Properties.Remove("user");
                }

                if (Application.Current.Properties.ContainsKey("password"))
                {
                    Application.Current.Properties.Remove("password");
                }

                Application.Current.Properties.Add("user", currentUser.user);
                Application.Current.Properties.Add("password", currentUser.password);
            }

            var mainPage = new MainPage(currentUser);

            await Navigation.PushModalAsync(mainPage);

            await DisplayAlert("Datos", currentUser.name, "OK");
        }
    }
}
