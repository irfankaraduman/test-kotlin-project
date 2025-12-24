using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.OleDb;
using System.Configuration;
using System.Data.SqlServerCe;

namespace RichesDotNetApp.Layer
{
    public class LocationDB
    {
        //Using SqlConnection to retrieve the data
        public static DataTable FindAtmByZip(String zip)
        {
            //using (OleDbConnection myConnection = new OleDbConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            //{
            //    myConnection.Open();
            //    string cmd = "SELECT * FROM [location] where atm = 'Yes' and zip = '" + zip + "'";
            //    OleDbDataAdapter adapter = new OleDbDataAdapter(cmd, myConnection);
            //    DataTable table = new DataTable();
            //    adapter.Fill(table);

            //    return table;
            //}

            using (SqlCeConnection connection = new SqlCeConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            {
                connection.Open();
                SqlCeCommand query = new SqlCeCommand("SELECT * FROM [location] where atm = 'Yes' and zip = '" + zip + "'", connection);
                SqlCeDataAdapter da = new SqlCeDataAdapter(query);
                DataTable table = new DataTable();
                da.Fill(table);
                return table;
            }
        }
        public static DataTable FindAtmLocationByAddress(String address, String city, String state)
        {
            //using (OleDbConnection myConnection = new OleDbConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            //{
            //    myConnection.Open();
            //    string cmd = "SELECT * FROM [location] where atm = 'Yes' and address = '" + address + "' and city = '" + city + "' and state = '" + state + "'";
            //    OleDbDataAdapter adapter = new OleDbDataAdapter(cmd, myConnection);
            //    DataTable table = new DataTable();
            //    adapter.Fill(table);

            //    return table;
            //}

            using (SqlCeConnection connection = new SqlCeConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            {
                connection.Open();
                SqlCeCommand query = new SqlCeCommand("SELECT * FROM [location] where atm = 'Yes' and address = '" + address + "' and city = '" + city + "' and state = '" + state + "'", connection);
                SqlCeDataAdapter da = new SqlCeDataAdapter(query);
                DataTable table = new DataTable();
                da.Fill(table);
                return table;
            }
        }

        public static DataTable FindBranchByZip(String zip)
        {
            //using (OleDbConnection myConnection = new OleDbConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            //{
            //    myConnection.Open();
            //    OleDbCommand query = new OleDbCommand("SELECT * FROM [location] where atm = 'Yes' and zip = @zip", myConnection);
            //    query.Parameters.AddWithValue("@zip", zip);
            //    OleDbDataAdapter da = new OleDbDataAdapter(query);
            //    DataTable table = new DataTable();
            //    da.Fill(table);
            //    return table;
            //}

            using (SqlCeConnection connection = new SqlCeConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            {
                connection.Open();
                SqlCeCommand query = new SqlCeCommand("SELECT * FROM [location] where atm = 'Yes' and zip = @zip", connection);
                query.Parameters.AddWithValue("@zip", zip);
                SqlCeDataAdapter da = new SqlCeDataAdapter(query);
                DataTable table = new DataTable();
                da.Fill(table);
                return table;
            }
        }

        public static DataTable FindBranchLocationByAddress(String address, String city, String state)
        {
            //using (OleDbConnection connection = new OleDbConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            //{
            //    connection.Open();
            //    OleDbCommand query = new OleDbCommand("SELECT * FROM [location] where atm = 'Yes' and address = @address and city = @city and state = @state", connection);
            //    query.Parameters.AddWithValue("@address", address);
            //    query.Parameters.AddWithValue("@city", city);
            //    query.Parameters.AddWithValue("@state", state);

            //    OleDbDataAdapter da = new OleDbDataAdapter(query);
            //    DataTable table = new DataTable();
            //    da.Fill(table);
            //    return table;

            //}

            using (SqlCeConnection connection = new SqlCeConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            {
                connection.Open();
                SqlCeCommand query = new SqlCeCommand("SELECT * FROM [location] where atm = 'Yes' and address = @address and city = @city and state = @state", connection);
                query.Parameters.AddWithValue("@address", address);
                query.Parameters.AddWithValue("@city", city);
                query.Parameters.AddWithValue("@state", state);
                SqlCeDataAdapter da = new SqlCeDataAdapter(query);
                DataTable table = new DataTable();
                da.Fill(table);
                return table;
            }
        }

    }
}