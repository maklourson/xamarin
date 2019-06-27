using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using Xamarin.Forms;
using TP4.Extensions;
using TP4.Services;
using TP4.Views;

namespace TP4.ViewModels
{
    public class AuthentificationViewModel : SimpleObservableObject
    {
        private int _IdCompte;
        public int IdCompte
        {
            get { return _IdCompte; }
            set
            {
                if (this.Set(ref this._IdCompte, value))
                {
                    this.CmdLogin.ChangeCanExecute();
                }
            }
        }

        private string _Password;
        public string Password
        {
            get { return _Password; }
            set
            {
                if (this.Set(ref this._Password, value))
                {
                    this.CmdLogin.ChangeCanExecute();
                    this.CmdDelete.ChangeCanExecute();
                }
            }
        }

        public Command CmdLogin { get; set; }
        public Command CmdDelete { get; set; }
        public Command<int> CmdAddDigit { get; set; }

        /// <summary>
        /// Liste des entiers 0-9 triés dans un ordre aléatoire
        /// </summary>
        public List<int> Entiers { get; set; }

        public AuthentificationViewModel()
        {
            this.CmdLogin = new Command(this.Login, this.CanLogin);
            this.CmdDelete = new Command(this.Delete, this.CanDelete);
            this.CmdAddDigit = new Command<int>(i => this.AddDigit(i));

            this.Entiers = Enumerable.Range(0, 10).GetRandomizedList();
        }

        private void AddDigit(int entier)
        {
            if (this.Password == null)
                this.Password = string.Empty;

            this.Password += entier;
        }

        private void Login()
        {
            if (BankService.Instance.Login(this.IdCompte, this.Password))
            {
                Application.Current.MainPage.Navigation.PushAsync(new UserAccountPage());
                this.IdCompte = 0;
                this.Password = null;
            }
            else
            {
                Application.Current.MainPage.DisplayAlert(
                    "Identifiants incorrects",
                    "Les identifiants que vous avez entré ne sont pas corrects.",
                    "Ok");
            }
        }
        private bool CanLogin()
        {
            return this.IdCompte.ToString().StartsWith("123") &&
                   this.IdCompte.ToString().Length == 10 &&
                   this.Password != null &&
                   this.Password.Length == 4;
        }

        private void Delete()
        {
            this.Password = null;
        }

        private bool CanDelete()
        {
            return !string.IsNullOrEmpty(this.Password);
        }
    }
}
