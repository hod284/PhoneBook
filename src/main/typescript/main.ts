const Phoneowner = document.getElementById('PhoneOwner')as HTMLInputElement;
const Phonenumber = document.getElementById('PhoneNumber')as HTMLInputElement;
const Phonegroup = document.getElementById('Group')as HTMLInputElement;
const Callingdate = document.getElementById('CallingDate')as HTMLInputElement;
const Callingorgetting = document.getElementById('callingorgetting')as HTMLInputElement;

const Columbutton_one = document.getElementById('getColum_one')as HTMLButtonElement;
const Columbutton_two = document.getElementById('getColum_two')as HTMLButtonElement;
const Columbutton_three = document.getElementById('getColum_three')as HTMLButtonElement;
const Columbutton_four = document.getElementById('getColum_four')as HTMLButtonElement;
const Columbutton_five = document.getElementById('getColum_five')as HTMLButtonElement;

const Modiftyphonebook = document.getElementById('modifyphoneNumber')as HTMLButtonElement;
const Addphonebook = document.getElementById('addthephonenumber')as HTMLButtonElement;
const Addphonehistory = document.getElementById('addphonehistory')as HTMLButtonElement;

const Init = document.getElementById('inite')as HTMLButtonElement;
const Onedelete = document.getElementById('onedelete')as HTMLButtonElement;

const getPhoneinfobynumber = document.getElementById('getphonebookclassbynumber') as HTMLButtonElement;
const getphoneinfobyowner1 = document.getElementById('getphonebookclass') as HTMLButtonElement;
const getphoneinfobyowner2 = document.getElementById('getphonehistoryclass') as HTMLButtonElement;
const getphoneinfobygroup = document.getElementById('Group') as HTMLButtonElement;
const getphoneinfobydate = document.getElementById('getphonehistoryclassbydate') as HTMLButtonElement;

const tableHead = document.getElementById('tableHead') as HTMLTableRowElement;
const tableBody = document.getElementById('tableBody') as HTMLTableSectionElement;

Columbutton_one.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/Column_Owner',null);
    
    if(re.ok)
    {
        SettingtheTable (re);
    }
    else
    {
        alert('컬럼이 비었습니다');
    }
}
catch(exception)
{
    console.log(exception);
}
});
Columbutton_one.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/Column_Owner',null);
    
    if(re.ok)
    {
        SettingtheTable (re);
    }
    else
    {
        alert('컬럼이 비었습니다');
    }
}
catch(exception)
{
    console.log(exception);
}
});
Columbutton_one.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/Column_Owner',null);
    
    if(re.ok)
    {
        SettingtheTable (re);
    }
    else
    {
        alert('컬럼이 비었습니다');
    }
}
catch(exception)
{
    console.log(exception);
}
});







function SettingtheTable (data)
{
    tableBody.innerHTML ="";
    tableHead.innerHTML ="";
    let array = Object.keys(data[0]);

    array.forEach(co => { 
        const column  = document.createElement(co) as HTMLElement;
        tableHead.appendChild(column);
    });
     data.forEach(row=>{
       const tr = document.createElement('tr');
       array.forEach(col =>{
        const td = document.createElement('td') as HTMLElement;
        td.textContent=row[col];
        tr.appendChild(td);
       });
       tableBody.appendChild(tr);
     })
}
