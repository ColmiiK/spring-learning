document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("current-year").textContent =
    new Date().getFullYear();

  const languageColors = {
    JavaScript: "#f1e05a",
    TypeScript: "#2b7489",
    HTML: "#e34c26",
    CSS: "#563d7c",
    Java: "#b07219",
    Python: "#3572A5",
    "C#": "#178600",
    PHP: "#4F5D95",
    Ruby: "#701516",
    Go: "#00ADD8",
    Swift: "#ffac45",
    Kotlin: "#F18E33",
    Rust: "#dea584",
  };

  const languageElements = document.querySelectorAll(".language-color");
  languageElements.forEach((element) => {
    const language = element.nextElementSibling.textContent;
    if (languageColors[language]) {
      element.style.backgroundColor = languageColors[language];
    }
  });
});
