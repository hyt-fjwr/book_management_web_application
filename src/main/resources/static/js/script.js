$(document).ready(function () {
  // ページの読み込み時に保存された状態を取得し、適用する
  var darkMode = localStorage.getItem("dark");
  if (darkMode === "true") {
    $("body").addClass("dark");
    $(".mode-text").text("ライトモード");
  } else {
    $(".mode-text").text("ダークモード");
  }

  // トグルスイッチのクリックイベント処理
  $(".toggle-switch").click(function () {
    $("body").toggleClass("dark");
    var isDarkMode = $("body").hasClass("dark");

    if (isDarkMode) {
      $(".mode-text").text("ライトモード");
    } else {
      $(".mode-text").text("ダークモード");
    }

    // チェックボックスの状態を保存する
    localStorage.setItem("dark", isDarkMode);
  });
});

$(document).ready(function () {
  $(".logoutBtn").click(function () {
    if (!confirm("ログアウトしてもよろしいですか？")) {
      return false;
    } else {
      window.location.href = "/book_list/logout";
    }
  });
});