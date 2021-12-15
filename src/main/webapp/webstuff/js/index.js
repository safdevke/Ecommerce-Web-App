var idArr = [];
if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    
    ready()
}

function ready() {

    var phoneIcon = document.getElementsByClassName('fa fa-phone');

    var emailIcon = document.getElementsByClassName('fa fa-envelope');

    var searchForm = document.getElementsByClassName('search_form');

    var product = document.getElementsByClassName('product-row');
    populateProducts(product, 9);

    var userAccount = document.getElementsByClassName('user_option_box');

    var cart = document.getElementsByClassName('cart-button')[0];
    cart.addEventListener('click', onCartClick);

    var navbar = document.getElementsByClassName('collapse navbar-collapse');

    var sliderSection = document.getElementsByClassName('slider_section');

    var readMore = document.getElementsByClassName('read_more-link');

    var buttons = document.getElementsByClassName('btn btn-danger');
    
    for(let i = 0; i < buttons.length; i++){
        var removeButton = buttons[i];
        removeButton.addEventListener('click', removeItem);
    }

    var quantityInputs = document.getElementsByClassName('cart-quantity-input');
    for(let i = 0; i < quantityInputs.length; i++){
        var input = quantityInputs[i];
        input.addEventListener('change', quantityChanged);
    }

}

function populateCart() {
    var cart = document.getElementsByClassName('cart-link');
    
}

function onAddToCartClick(event) {
    var button = document.getElementsByClassName('add_cart_btn');
    var buttonClicked = event.target;
    var productId = parseInt(buttonClicked.value);
    if (!(idArr.includes(productId))) {
        idArr.push(parseInt(buttonClicked.value));
    }else {
        alert("Product already added to cart");
    }
    localStorage.setItem('idArr', JSON.stringify(idArr));
    console.log(idArr);
}

function onCartClick (event) {

    var buttonClicked = event.target;

    function cartPage() {
        document.write('');
        document.write('<html>');
        document.write("<head>");
        document.write("	<title>Shopping Cart UI<\/title>");
        document.write("	<link rel=\"stylesheet\" type=\"text\/css\" href=\"css\/cart-style.css\">");
        document.write("	<link href=\"https:\/\/fonts.googleapis.com\/css?family=Open+Sans:300,400,600,700,900\" rel=\"stylesheet\">");
        document.write("<\/head>");
        document.write("<body>");
        document.write("   <div class=\"CartContainer\">");
        document.write("   	   <div class=\"Header\" id =\"hdr\">");
        document.write("   	   	<h3 class=\"Heading\">Shopping Cart<\/h3>");
        document.write("   	   	<h5 class=\"Action\">");
        document.write("                 <button class = \"remove_all\" type=\"button\">Remove all<\/button>");
        document.write("   	   <\/div>");
        document.write("		  <div class=\"Cart-Items\"><\/div>");
        document.write("   	 <div class=\"checkout\">");
        document.write("   	 <div class=\"total\">");
        document.write("   	 	<div>");
        document.write("   	 		<div class=\"Subtotal\">Sub-Total<\/div>");
        document.write("   	 		<div class=\"items\">0 items<\/div>");
        document.write("   	 	<\/div>");
        document.write("   	 	<div class=\"total-amount\">$0<\/div>");
        document.write("   	 <\/div>");
        document.write("   	 <button class=\"button-checkout\">Checkout<\/button><\/div>");
        document.write("   	 <div class=\"back\">");
        document.write("   	 <a href=\"../webstuff/index.html\"><button class=\"button-back\">Back<\/button></a><\/div>");
        document.write("   <\/div>");
        document.write("   <!-- index -->");
        document.write("  <script src=\"index.js\" async><\/script>");
        document.write("<\/body>");
        document.write("<\/html>");
        document.close();
    }

    function insertAfter(referenceNode, newNode) {
        referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
    }

    function cartItems(response, status) {
        if (status == 200) {
            console.log('Success: ' + status);
        } else {
            console.log('Error: ' + status);
        }
        var productsArr = JSON.parse(response);
        
        cartPage();
        
        var cartItems = document.getElementsByClassName('Cart-items')[0];
        
        idArr = JSON.parse(localStorage.getItem('idArr'));


        if (idArr == null) { 
            var myDiv = $("#hdr")[0];
            $(myDiv).find('h3').after("<h2>Cart is Empty</h2>");
            var removeAllButton = document.getElementsByClassName('remove_all')[0];
            var checkoutButton = document.getElementsByClassName('button-checkout')[0];
            removeAllButton.disabled = true;
            checkoutButton.disabled = true;
        }

        var strVar="";
        for (var i = 0; i < idArr.length; i++) {

            if (i > 0) {
                strVar += "     <div class=\"Cart-Items\">";
            }
            var productObj = productsArr[idArr[parseInt(i)] - 1];
            strVar += "   	   	  <div class=\"image-box\">";
            strVar += "   	   	  	<img src=\"" + productObj.productUrl +"\" style={{ height=\"120px\" }} \/>";
            strVar += "   	   	  <\/div>";
            strVar += "   	   	  <div class=\"about\">";
            strVar += "   	   	  	<h1 class=\"title\">" + productObj.productName.split(" ")[0] + "<\/h1>";
            strVar += "   	   	  	<h3 class=\"subtitle\">" + "<\/h3>";
            strVar += "   	   	  	<span class=\"description\">" + productObj.productDesc.split(" ")[0] + "<\/span>";
            strVar += "   	   	  	<img src=\"https://www.pngkey.com/png/full/261-2619381_chitr-veg-symbol-svg-veg-and-non-veg.png\" style={{ height=\"30px\" }}\/>";
            strVar += "   	   	  <\/div>";
            strVar += "   	   	  <div class=\"counter\">";
            strVar += "   	   	  	<div class=\"btn\">";
            strVar += "                 <button class=\"add\" type=\"button\">+<\/button>";
            strVar += "   	   	  	<\/div>";
            strVar += "   	   	  	<div class=\"count\">1<\/div>";
            strVar += "   	   	  	<div class=\"btn\">";
            strVar += "                 <button class=\"sub\" type=\"button\">-<\/button>";
            strVar += "   	   	  	<\/div>";
            strVar += "   	   	  <\/div>";
            strVar += "   	   	  <div class=\"prices\">";
            strVar += "   	   	  	<div class=\"amount\">$" + productObj.unitPrice + "<\/div>";
            strVar += "   	   	  	<div class=\"remove\">";
            strVar += "                 <button class=\"remove_btn\" type=\"button\">Remove<\/button>";
            strVar += "   	   	  <\/div>";
            strVar += "   	   <\/div>";
            strVar += "   	   <\/div>";

            if (i < 1) {
                cartItems.innerHTML = strVar;
                strVar = "";
            }
        }
        cartItems.outerHTML += strVar;

        var items = document.getElementsByClassName('items')[0];
        items.innerHTML = idArr.length + " items";

        var removeAll = document.getElementsByClassName('remove_all');
        removeAll[0].addEventListener('click', removeAllItems);

        var remove = document.getElementsByClassName('remove_btn');
        for (var i = 0; i < remove.length; i++) {
            remove[i].addEventListener('click', removeItem);
        }

        var add = document.getElementsByClassName('add');

        var priceDisplayed = Array.from(document.getElementsByClassName('amount'));
        var priceArr = [];
        for (var i = 0; i < priceDisplayed.length; i++) {
            priceArr.push(parseInt(priceDisplayed[i].innerHTML.substring(1)));
        }
            
        for (var i = 0; i < add.length; i++) {
            add[i].addEventListener('click', addItem.bind(this, event, [i, priceArr]));
        }

        var sub = document.getElementsByClassName('sub');
        for (var i = 0; i < sub.length; i++) {
            sub[i].addEventListener('click', subItem.bind(this,event, [i, priceArr]));
        }

        function removeAllItems(event) {
            var cartItems = Array.from(document.getElementsByClassName('Cart-Items'));
            localStorage.removeItem('idArr');
            for (var i = 0; i < cartItems.length; i++) {
                cartItems[i].remove();
            }
            var buttonClicked = event.target;
            buttonClicked.disabled = true;
            document.getElementsByClassName('items')[0].innerHTML = "0 items";
            document.getElementsByClassName('total-amount')[0].innerHTML = "$0.00";
        }

        function removeItem(event) {
            removeButton = event.target;
            var removeId = removeButton.parentNode.parentNode.parentNode.id;
            var idArr = JSON.parse(localStorage.getItem('idArr'));
            var index = idArr.indexOf(removeId);
            idArr.splice(index, 1);
            localStorage.setItem('idArr', JSON.stringify(idArr));
            removeButton.parentNode.parentNode.parentNode.remove();
            document.getElementsByClassName('items')[0].innerHTML = idArr.length + " items";
            document.getElementsByClassName('total-amount')[0].innerHTML = "$" + subTotal();
        }

        function subItem(event, arr) {
            var i = arr[0];
            var priceArr = arr[1];
            var currentPrice = priceArr[i];
            var countItem = document.getElementsByClassName('count')[i];
            if (parseInt(countItem.innerHTML) > 1) {
                countItem.innerHTML = parseInt(countItem.innerHTML) - 1;
            }
            var display = document.getElementsByClassName('amount')[i];
            var finalPrice =  (currentPrice * parseInt(countItem.innerHTML));
            var rounded = Math.round(finalPrice * 100) / 100;
            display.innerHTML = "$" + rounded;
            document.getElementsByClassName('total-amount')[0].innerHTML = "$" + subTotal();
            
        }

        function addItem(event, arr) {
            var i = arr[0];
            var priceArr = arr[1];
            var currentPrice = priceArr[i];
            var countItem = document.getElementsByClassName('count')[i];
            countItem.innerHTML = parseInt(countItem.innerHTML) + 1;
            var display = document.getElementsByClassName('amount')[i];
            var finalPrice =  (currentPrice * parseInt(countItem.innerHTML));
            var rounded = Math.round(finalPrice * 100) / 100;
            display.innerHTML = "$" + rounded;
            document.getElementsByClassName('total-amount')[0].innerHTML = "$" + subTotal();

        }

        function subTotal() {
            var subTotal = 0;
            var amount = document.getElementsByClassName('amount');
            for (var i = 0; i < amount.length; i++) {
                subTotal += parseInt(amount[i].innerHTML.substring(1));
            }
            return subTotal;
        }

        function onClickCheckout() {
            var checkoutButton = document.getElementsByClassName('button-checkout')[0];
            checkoutButton.addEventListener('click', onCheckoutClick); 
            
        }

        function onCheckoutClick(event) {
            
        }
    }

    var url = 'http://omoka.ml/api/products/all';
    var method = 'GET';
    var data = null;
    ajaxReq(url, method, data, cartItems);

}

function ajaxReq(url, method, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(data);
    xhr.onload = function () {
        callback(xhr.response,xhr.status);
    };

}

function populateProducts(product, num) {

    function productJSON(response,status) {
        if (status == 200) {
            console.log('Success: ' + status + ': Products Loaded');
        } else {
            console.log('Error: ' + status);
        }
        var productsArr = JSON.parse(response);
        var strVar="";
        for (let i = 0; i < num; i++) {
            var productObj = productsArr[i];
            strVar += "<div class=\"col-sm-6col-lg-4\">";
            strVar += "          <div class=\"box\">";
            strVar += "            <div class=\"img-box\">";
            strVar += "              <img src=\"" + productObj.productUrl + "\" alt=\"\">";
            strVar += "                <span>";
            strVar += "                 <button class=\"add_cart_btn\", type=\"button\", value =" + productObj.productId + ">";
            strVar += "                  Add To Cart";
            strVar += "                <\/span>";
            strVar += "              <\/a>";
            strVar += "            <\/div>";
            strVar += "            <div class=\"detail-box\">";
            strVar += "              <h5>";
            strVar += "" +           productObj.productName+ "";
            strVar += "              <\/h5>";
            strVar += "              <div class=\"product_info\">";
            strVar += "                <h5>";
            strVar += "                  <span>$<\/span>" + productObj.unitPrice + "";;
            strVar += "                <\/h5>";
            strVar += "                 <p hidden id=\"prodId\">" + productObj.productId + "<\/p>";
            strVar += "                <div class=\"star_container\">";
            strVar += "                  <i class=\"fa fa-star\" aria-hidden=\"true\"><\/i>";
            strVar += "                  <i class=\"fa fa-star\" aria-hidden=\"true\"><\/i>";
            strVar += "                  <i class=\"fa fa-star\" aria-hidden=\"true\"><\/i>";
            strVar += "                  <i class=\"fa fa-star\" aria-hidden=\"true\"><\/i>";
            strVar += "                  <i class=\"fa fa-star\" aria-hidden=\"true\"><\/i>";
            strVar += "                <\/div>";
            strVar += "              <\/div>";
            strVar += "            <\/div>";
            strVar += "          <\/div>";
            strVar += "        <\/div>";
            strVar += "       <\/div>";
            
        }
        product[0].innerHTML += strVar;
        var addToCartButtons = document.getElementsByClassName('add_cart_btn');
        for (let i = 0; i < addToCartButtons.length; i++) {
            addToCartButtons[i].addEventListener('click', onAddToCartClick);
        }
    }

    var url = 'http://omoka.ml/api/products/all';
    var method = 'GET';
    var data = null;
    ajaxReq(url, method, data, productJSON);
}





