var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const Phoneowner = document.getElementById('PhoneOwner');
const Phonenumber = document.getElementById('PhoneNumber');
const Phonegroup = document.getElementById('inGroup');
const Searchingdate = document.getElementById('SearchingDate');
const Callingorgetting = document.getElementById('callingorgetting');
const Columbutton_one = document.getElementById('getColum_one');
const Columbutton_two = document.getElementById('getColum_two');
const Columbutton_three = document.getElementById('getColum_three');
const Columbutton_four = document.getElementById('getColum_four');
const Columbutton_five = document.getElementById('getColum_five');
const Modiftyphonebook = document.getElementById('modifyphoneNumber');
const Addphonebook = document.getElementById('addthephonenumber');
const Addphonehistory = document.getElementById('addphonehistory');
const Init = document.getElementById('inite');
const Onedelete = document.getElementById('onedelete');
const getPhoneinfobynumber = document.getElementById('getphonehistoryclassbynumber');
const getphoneinfobyowner1 = document.getElementById('getphonebookclass');
const getphoneinfobyowner2 = document.getElementById('getphonehistoryclass');
const getphoneinfobygroup = document.getElementById('Group');
const getphoneinfobydate = document.getElementById('getphonehistoryclassbydate');
const tableHead = document.getElementById('tableHead');
const tableBody = document.getElementById('tableBody');
Columbutton_one.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        const re = yield fetch('/api/Column_Owner');
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            alert('컬럼이 비었습니다');
        }
    }
    catch (exception) {
        console.log(exception);
    }
}));
Columbutton_two.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        const re = yield fetch('/api/Column_Number');
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            alert('컬럼이 비었습니다');
        }
    }
    catch (exception) {
        console.log(exception);
    }
}));
Columbutton_three.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        const re = yield fetch('/api/Column_PhoneGroup');
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            alert('컬럼이 비었습니다');
        }
    }
    catch (exception) {
        console.log(exception);
    }
}));
Columbutton_four.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        const re = yield fetch('/api/Column_datetime');
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            alert('컬럼이 비었습니다');
        }
    }
    catch (exception) {
        console.log(exception);
    }
}));
Columbutton_five.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        const re = yield fetch('/api/Column_callingorgetting');
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            alert('컬럼이 비었습니다');
        }
    }
    catch (exception) {
        console.log(exception);
        alert('컬럼이 비었습니다');
    }
}));
Init.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        const re = yield fetch('/api/init', {
            method: 'DELETE'
        });
        if (re.ok) {
            alert('초기화 하였습니다');
        }
        else {
            alert('실패했습니다');
        }
    }
    catch (exception) {
        console.log(exception);
        alert('실패했습니다');
    }
}));
Onedelete.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phoneowner.value === null || Phoneowner.value.trim.toString() === "") {
            alert("이름을 입력하세요");
            return;
        }
        if (Phonenumber.value === null || Phonenumber.value.trim.toString() === "") {
            alert("번호를 입력하세요");
            return;
        }
        if (Phonegroup.value === null || Phonegroup.value.trim.toString() === "") {
            alert("그룹을 입력하세요");
            return;
        }
        const body = {
            Name: Phoneowner.value.trim(),
            Number: Phonenumber.value.trim(),
            Group: Phonegroup.value.trim()
        };
        const re = yield fetch('/api/onedelete', {
            method: 'DELETE',
            headers: { 'Content-Type': "application/json" },
            body: JSON.stringify(body)
        });
        if (re.ok) {
            alert("삭제 완료");
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
    }
}));
getphoneinfobyowner1.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phoneowner.value === null || Phoneowner.value.trim.toString() === "") {
            alert("이름을 입력하세요");
            return;
        }
        const re = yield fetch(`/api/ow/${Phoneowner.value}`);
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert("부모에서 받은 데이터 없음");
    }
}));
getphoneinfobyowner2.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phoneowner.value === null || Phoneowner.value.trim.toString() === "") {
            alert("이름을 입력하세요");
            return;
        }
        const re = yield fetch(`/api/how/${Phoneowner.value}`);
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert("자식에서 받은 데이터 없음");
    }
}));
getphoneinfobygroup.addEventListener("click", () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phonegroup.value === null) {
            alert("그룹을 입력하세요");
            return;
        }
        const re = yield fetch(`/api/gr/${Phonegroup.value}`);
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert(" 데이터 없음");
    }
}));
getphoneinfobydate.addEventListener('click', () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Searchingdate.value === null || Searchingdate.value.trim.toString() === "") {
            alert("검색할 연도나 달을 입력하세요");
            return;
        }
        const re = yield fetch(`/api/da/${Searchingdate.value.trim()}`);
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert(" 데이터 없음");
    }
}));
getPhoneinfobynumber.addEventListener('click', () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phonenumber.value === null || Phoneowner.value.trim.toString() === "") {
            alert("번호를 입력하세요");
            return;
        }
        const re = yield fetch(`/api/nu/${Phoneowner.value.trim()}`);
        if (re.ok) {
            const data = yield re.json();
            console.log(data);
            SettingtheTable(data);
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert(" 데이터 없음");
    }
}));
Modiftyphonebook.addEventListener('click', () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phoneowner.value === null || Phoneowner.value.trim.toString() === "") {
            alert("주인를 입력하세요");
            return;
        }
        if (Phonenumber.value === null || Phonenumber.value.trim.toString() === "") {
            alert("번호를 입력하세요");
            return;
        }
        if (Phonegroup.value === null || Phonegroup.value.trim.toString() === "") {
            alert("그룹을 입력하세요");
            return;
        }
        const body = {
            Name: Phoneowner.value.trim(),
            Number: Phonenumber.value.trim(),
            Group: Phonegroup.value.trim()
        };
        const re = yield fetch('/api/changephonbookinfo', {
            method: 'PATCH',
            headers: { 'Content-Type': "application/json" },
            body: JSON.stringify(body)
        });
        if (re.ok) {
            alert("삭제 완료");
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert(" 데이터 없음");
    }
}));
Addphonebook.addEventListener('click', () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phoneowner.value === null || Phoneowner.value.trim.toString() === "") {
            alert("주인를 입력하세요");
            return;
        }
        if (Phonenumber.value === null || Phonenumber.value.trim.toString() === "") {
            alert("번호를 입력하세요");
            return;
        }
        if (Phonegroup.value === null || Phonegroup.value.trim.toString() === "") {
            alert("그룹을 입력하세요");
            return;
        }
        const body = {
            Name: Phoneowner.value.trim(),
            Number: Phonenumber.value.trim(),
            Group: Phonegroup.value.trim()
        };
        console.log(body);
        const re = yield fetch('/api/addthnumber', {
            method: 'POST',
            headers: { 'Content-Type': "application/json" },
            body: JSON.stringify(body)
        });
        console.log(re);
        if (re.ok) {
            alert("추가 완료");
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert(" 데이터 없음");
    }
}));
Addphonehistory.addEventListener('click', () => __awaiter(this, void 0, void 0, function* () {
    try {
        if (Phoneowner.value === null || Phoneowner.value.trim.toString() === "") {
            alert("오너를 입력하세요");
            return;
        }
        if (Callingorgetting.value === null || Callingorgetting.value.trim.toString() === "") {
            alert("그룹을 입력하세요");
            return;
        }
        const body = {
            CallingName: Phoneowner.value.trim(),
            CallingorGetting: Phonenumber.value.trim()
        };
        const re = yield fetch('/api/called', {
            method: 'POST',
            headers: { 'Content-Type': "application/json" },
            body: JSON.stringify(body)
        });
        if (re.ok) {
            alert("삭제 완료");
        }
        else {
            throw new Error(yield re.text());
        }
    }
    catch (exception) {
        console.log(exception);
        alert(" 데이터 없음");
    }
}));
function SettingtheTable(data) {
    tableBody.innerHTML = "";
    tableHead.innerHTML = "";
    let array = Object.keys(data[0]);
    array.forEach(co => {
        const column = document.createElement(co);
        tableHead.appendChild(column);
    });
    data.forEach(row => {
        const tr = document.createElement('tr');
        array.forEach(col => {
            const td = document.createElement('td');
            if (col === 'phone_callingorgetting') {
                td.textContent = row[col] === 1 ? '수신' : '발신';
            }
            else {
                td.textContent = row[col];
            }
            tr.appendChild(td);
        });
        tableBody.appendChild(tr);
    });
}
