using System;
using TP4.Services;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace TP4
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();

            MainPage = new NavigationPage(new Views.AuthentificationPage());
        }

        protected override void OnStart()
        {
            // Handle when your app starts
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
            Application.Current.Properties["LastSleep"] = DateTime.Now;
        }

        protected override void OnResume()
        {
            // Handle when your app resumes

            if(Application.Current.Properties.TryGetValue("LastSleep", out object lastSleepObject))
            {
                if (DateTime.TryParse(lastSleepObject.ToString(), out DateTime lastSleep))
                {
                    TimeSpan diff = DateTime.Now - lastSleep;

                    if (diff.TotalSeconds > 5)
                    {
                        BankService.Instance.Logoff();
                    }
                }
            }
        }
    }
}
