using System;
using System.Collections.Generic;
using System.Text;
using TP4.Models;
using Xamarin.Forms;

namespace TP4.Services
{
    public class BankService
    {
        private BankService() { }
        private static BankService _Instance;
        public static BankService Instance
        {
            get
            {
                if (_Instance == null) _Instance = new BankService();

                return _Instance;
            }
        }

        private UserBank _CurrentUser;

        public UserBank GetCurrentUser()
        {
            return _CurrentUser;
        }

        public bool IsAuthenticated()
        {
            return this.GetCurrentUser() != null;
        }

        public void Logoff()
        {
            this._CurrentUser = null;

            // On redirige l'utilisateur sur la page d'accueil
            Application.Current.MainPage.Navigation.PopToRootAsync();
        }

        public bool Login(int idCompte, string password)
        {
            if (idCompte == 1234567890 && password == "1234")
            {
                this._CurrentUser = new UserBank
                {
                    Email = "userbank@email",
                    IdCompte = idCompte,
                    Mdp = password,
                    Nom = "User",
                    Prenom = "Bank"
                };
                return true;
            }

            return false;
        }
    }
}
