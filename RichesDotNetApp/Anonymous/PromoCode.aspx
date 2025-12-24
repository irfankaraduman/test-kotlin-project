<%@ Page Title="" Language="C#" MasterPageFile="~/Anonymous/AnonymousMasterPage.master" AutoEventWireup="true" CodeBehind="PromoCode.aspx.cs" Inherits="RichesDotNetApp.Anonymous_PromoCode" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="Server">
<table>
<tr>
<td>Promo Code<br />
    Enter your email address to get speical offer:<br />
    <br />
    <asp:TextBox ID="TextBox1" runat="server"/>
    <asp:Button ID="Button1" runat="server" Text="Register" 
        onclick="Button1_Click" />
    <br />
    <asp:Label ID="Label1" runat="server"></asp:Label>
    </td>
</tr>
</table>
</asp:Content>