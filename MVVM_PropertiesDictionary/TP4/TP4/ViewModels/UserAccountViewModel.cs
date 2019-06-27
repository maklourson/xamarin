using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;
using TP4.Models;
using TP4.Services;
using Xamarin.Forms;

namespace TP4.ViewModels
{
    public class UserAccountViewModel : SimpleObservableObject
    {
        private UserBank _userBank;

        public string Email { get => this._userBank?.Email; }
        public string MessageBienvenue { get => $"Bienvenue Mr {this._userBank?.Prenom} {this._userBank?.Nom}"; }

        public Command CmdLogoff { get; set; }

        public UserAccountViewModel()
        {
            if (!BankService.Instance.IsAuthenticated())
            {
                Application.Current.MainPage.Navigation.PopToRootAsync();
                return;
            }

            this._userBank = BankService.Instance.GetCurrentUser();

            this.CmdLogoff = new Command(() => BankService.Instance.Logoff());
        }
    }
}
