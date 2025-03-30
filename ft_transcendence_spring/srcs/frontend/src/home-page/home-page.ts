import { navigateTo } from "../index.js";
import { initHomeFetches } from "./home-fetch.js"

/**
 * @brief Inits the associated Home Events
 * @note Export because it'll be called on index.js
 */
export function initHomeEvents() {
	initHomeFetches();
	setHomeAttributes();
	dropDown();
}


/**
 * @brief Displays username on welcoming message
 */
function setHomeAttributes() {
	const usernameText = document.getElementById("username") as HTMLSpanElement;
	if (usernameText) {
		const username = localStorage.getItem("username");
		if (username)
			usernameText.innerText = " " + username;
	}
}

/**
 * @brief Shows/hides a dropdown when the profile picture is clicked
 * @note When clicked outside dropdown dissapears
 */
function dropDown(){
	const dropdownButton = document.getElementById("profile-button");
	const dropdownOptions = document.getElementById("profile-options");
	if (!dropdownButton || !dropdownOptions)
		return;

	dropdownButton.addEventListener("click", () => {
		dropdownButton.focus();
	});
	dropdownButton.addEventListener("focus", () => {
		dropdownOptions.style.display = "block";
	});
	dropdownButton.addEventListener("blur", () => {
		dropdownOptions.style.display = "none";
	});
	dropdownOptions.addEventListener("mousedown", (e) => {
		const target = e.target as HTMLElement;
		const key = target.getAttribute('profile_key');
		if (key)
			navigateProfileOptions(key);
	});
}

/**
 * @brief Navigates you to the selected profile picture option
 * @param option The option you choosed (Modify profile, Settings or Log Out)
 */
function navigateProfileOptions(option: string) {
	const dropdownButton = document.getElementById("profile-button");
	if (!dropdownButton)
		return;
	const profileOptions = [
		{ key: 'modifyProfile', label: '/modify-profile' },
		{ key: 'settings', label: '/settings' },
		{ key: 'logOut', label: '/login' },
	];

	const profileOption = profileOptions.find((elem) => elem.key == option);
	if (profileOption)
		navigateTo(profileOption.label);
}