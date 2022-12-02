const adddbtn = document.querySelector(".adddbtn");
const add = document.querySelector(".add");
adddbtn.onclick = function () {
    add.classList.toggle("active");
};
function validation1() {
    var user = document.getElementById("user").value;
    var text1 = document.getElementById("text1");
    if (user.length < 3) {
        text1.innerHTML = "*Username must be more than 3 character";
        text1.style.color = "#ff0000";
    } else if (user.length > 15) {
        text1.innerHTML = "*Username must be less than 15 character";
        text1.style.color = "#ff0000";
    } else {
        text1.innerHTML = "*Username is valid";
        text1.style.color = "#00ff00";
    }
}
function validation2() {
    var name = document.getElementById("name").value;
    var text2 = document.getElementById("text2");
    if (name.length < 3) {
        text2.innerHTML = "*Name must be more than 3 character";
        text2.style.color = "#ff0000";
    } else {
        text2.innerHTML = "*Name is Valid";
        text2.style.color = "#00ff00";
    }
}
function validation3() {
    var user = document.getElementById("pass").value;
    var text3 = document.getElementById("text3");
    if (user.length < 6) {
        text3.innerHTML = "*Pass must be more than 6 character";
        text3.style.color = "#ff0000";
    } else if (user.length > 15) {
        text3.innerHTML = "*Pass must be less than 15 character";
        text3.style.color = "#ff0000";
    } else {
        text3.innerHTML = "*Pass is valid";
        text3.style.color = "#00ff00";
    }
}
function validation() {
    var email = document.getElementById("email").value;
    var text = document.getElementById("text");
    var pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if (email.match(pattern)) {
        text.innerHTML = "*Your Email Address is Valid";
        text.style.color = "#00ff00";
    } else {
        text.innerHTML = "*Please Enter Valid Email Address";
        text.style.color = "#ff0000";
    }
}
function validationCode() {
    var scode = document.getElementById("scode").value;
    var txtcode = document.getElementById("txtcode");
    if (scode.length > 15) {
        txtcode.innerHTML = "*Subject Code must be less than 15 character";
        txtcode.style.color = "#ff0000";
    } else {
        txtcode.innerHTML = "*Subject Code is Valid";
        txtcode.style.color = "#00ff00";
    }
}
function validationName() {
    var scode = document.getElementById("sname").value;
    var txtcode = document.getElementById("txtname");
    if (scode.length > 150) {
        txtcode.innerHTML = "*Subject Name must be less than 150 character";
        txtcode.style.color = "#ff0000";
    } else {
        txtcode.innerHTML = "*Subject Name is Valid";
        txtcode.style.color = "#00ff00";
    }
}
function validateAuthor() {
    var txtcode = document.getElementById("txtaname");
    var aname = document.getElementById("aname");
    txtcode.innerHTML = "*Look good";
    txtcode.style.color = "#00ff00";
    if (aname == "name") {
        txtcode.innerHTML = "*Pls choose one option";
        txtcode.style.color = "#ff0000";
    }
}
function searchByName(param) {
    var txtSearch = param.value;
    const liked = document.querySelector("#orderne").value;
    const likeda = document.querySelector("#ordernea").value;
    $.ajax({
        url: "/g1/SearchSubject",
        type: "get",
        data: {
            txt: txtSearch,
            lkkk: liked,
            lkkka: likeda
        },
        success: function (data) {
            var slide = document.getElementById("slide");
            slide.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}
function loadMore() {
    const like = document.querySelector(".search").value;
    var amount = document.getElementsByClassName("count").length;
    const liked = document.querySelector("#orderne").value;
    const likeda = document.querySelector("#ordernea").value;
    const het = document.querySelector("#het").value;
    $.ajax({
        url: "/g1/LoadList",
        type: "get",
        data: {
            likes: like,
            lkkk: liked,
            total: amount,
            lkkka: likeda,
            het: het
        },
        success: function (data) {
            var slide = document.getElementById("slide");
            slide.innerHTML += data;
        },
        error: function (xhr) {
        }
    });
}
function order() {
    const like = document.querySelector(".search").value;
    const liked = document.querySelector("#orderne").value;
    const likeda = document.querySelector("#ordernea").value;
    $.ajax({
        url: "/g1/SearchSubject",
        type: "get",
        data: {
            likes: like,
            lkkk: liked,
            lkkka: likeda
        },
        success: function (data) {
            var slide = document.getElementById("slide");
            slide.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}
$(window).scroll(function () {
    if ($(window).scrollTop() >= ($(document).height() - $(window).height() - 200)) {
        loadMore();
    }
});
function changetype() {
    document.querySelector("#page").style.display = "block";
    document.querySelector("#gobtn").style.display = "block";
    document.querySelector("#showpage").style.display = "none";
}
function AddStudent() {
    var Add = "Add";
    $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
            Add: Add
        },
        success: function (data) {
            var slide = document.getElementById("slide");
            slide.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}
function outAdd() {
    var Add = "OutAdd";
    $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
            Add: Add
        },
        success: function (data) {
            var slide = document.getElementById("slide");
            slide.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}
function filterAdd() {
    const SearchBy = document.querySelector(".SearchBy").value;
    const searchTxT = document.querySelector(".searchTxT").value;
    const page = document.querySelector("#page").value;
    var Add = "Add";
    $.ajax({
        url: "/g1/ClassUser4Admin",
        type: "get",
        data: {
            Add: Add,
            SearchBy: SearchBy,
            searchTxT: searchTxT,
            page: page
        },
        success: function (data) {
            var slide = document.getElementById("slide");
            slide.innerHTML = data;
        },
        error: function (xhr) {
        }
    });
}