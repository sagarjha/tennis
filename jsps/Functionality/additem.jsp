<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tennis World - Manage Items</title>
        <link href="style.css" rel="stylesheet" type="text/css">        
    </head>
    <h3 align="center">
        Add Item
    </h3>
    <hr>
    <body>
        <table width="500" border="0" cellspacing="0" cellpading="0">
            <form name="additem" method="post" action="../tennis">
              <tr>
                  <td width="401"><div align="center"><b>Product Type : </b></div></td>
                   <td width="400"><div align="left">
                   <select id="pType" name="pType">
                     <option value="Racquet">Racquet</option>
                     <option value="Ball">Ball</option>
                     <option value="Shoes">Shoes</option>
                     <option value="Apparel">Apparel</option>
                     <option value="Bands">Bands</option>
                     <option value="Shocker">Shocker</option>
                     <option value="Grip">Grip</option>
                     <option value="String">String</option>
                     
                   </select>
                   </td>
              </tr>
              <tr>
                  <td width="401"><div align="center"><b>Product Brand : </b></div></td>
                  <td width="399"><input type="text" name="brandName"></td>
              </tr>
			  <tr>
                  <td width="401"><div align="center"><b>Price : </b></div></td>
                  <td width="399"><input type="text" name="price"></td>
              </tr>
                  <td width="600"><div align="right"><br><input type="Submit" name="ADDITEM" value = "Add"></td>
              </tr>
        </table>
    </body>
</html>
