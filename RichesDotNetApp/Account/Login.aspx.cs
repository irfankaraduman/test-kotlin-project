using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Account_Login : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        //if (!this.IsPostBack)
        //    FormsAuthentication.SignOut();

        if (Request.IsAuthenticated)
        {
            if (User.IsInRole("admin"))
            {
                Response.Redirect("~/Admin");
            }
            else
            {
                Response.Redirect("~/Users/AccountSummary.aspx");
            }
        }
    }
    protected void Login1_LoginError(object sender, EventArgs e)
    {
        Login l = (Login)sender;
        l.FailureText="Invalid credentials for "+l.UserName;
    }

    //protected void LoginUser_Authenticate(object sender, AuthenticateEventArgs e)
    //{
    //    if (!e.Authenticated)
    //    {
    //        Login l = (Login)sender;
    //        FormsAuthentication.SetAuthCookie(l.UserName, true);
    //    }
    //    // FormsAuthentication.SetAuthCookie("eddie", true);
    //    //if (e.Authenticated)
    //    //{
    //    //    Response.Redirect("~/Users/AccountSummary.aspx");
    //    //}
    //    //if (!e.Authenticated) {
    //    //    FormsAuthentication.SetAuthCookie("eddie", true);
    //    //}
    //}
}
