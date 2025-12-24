using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Routing;
using System.ServiceModel.Activation;
using System.Web.Security;
using System.Web.SessionState;
using RichesDotNetApp.Layer.BO;


namespace RichesDotNetApp
{
    public class Global : System.Web.HttpApplication
    {

        protected void Application_Start(object sender, EventArgs e)
        {
            //FormsAuthentication.SignOut();
            RouteTable.Routes.Add(new ServiceRoute("rest", new WebServiceHostFactory(), typeof(RichesDotNetApp.Layer.BO.RestfulService)));
        }

        protected void Session_Start(object sender, EventArgs e)
        {

        }

        protected void Application_BeginRequest(object sender, EventArgs e)
        {

        }

        protected void Application_AuthenticateRequest(object sender, EventArgs e)
        {

        }

        protected void Application_Error(object sender, EventArgs e)
        {

        }

        protected void Session_End(object sender, EventArgs e)
        {

        }

        protected void Application_End(object sender, EventArgs e)
        {

        }
    }
}