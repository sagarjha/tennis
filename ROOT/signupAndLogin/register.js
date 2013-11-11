function register()
{
    var select = document.getElementById('role').value;
    switch(select)
    {
    case "1":
      //open up form for Promotion Attribute Changes
        alert("Promo attr works");
        document.location.href="../generalCase.jsp";

      break;
    case "2":
      //open up form for Promotion approval order change
        alert("Promo order works");
        document.location.href="../generalCase.jsp";
      break;
    case "3":
      //open up form for Changing default approver on promotion
        alert("Default Approval works");
        document.location.href="../generalCase.jsp";
          break;
    default:


    }
    return false;

}
