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
        SettingtheTable (re,1);
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
Columbutton_two.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/Column_Number',null);
    
    if(re.ok)
    {
        SettingtheTable (re,2);
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
Columbutton_three.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/Column_PhoneGroup',null);
    
    if(re.ok)
    {
        SettingtheTable (re,3);
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
Columbutton_four.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/Column_datetime',null);
    
    if(re.ok)
    {
        SettingtheTable (re,4);
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
Columbutton_five.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/Column_callingorgetting',null);
    
    if(re.ok)
    {
        SettingtheTable (re,5);
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
Init.addEventListener("click",async ()=>{
    
    try{
    const re = await fetch('/api/init',{
        method: 'DELETE'
    });
    
    if(re.ok)
    {
         alert('초기화 하였습니다');
    }
    else
    {
        alert('실패했습니다');
    }
}
catch(exception)
{
    console.log(exception);
}
});
Onedelete.addEventListener("click",async ()=>{
    try{
     if(Phoneowner.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("이름을 입력하세요");
        return;
    }
       if(Phonenumber.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("번호를 입력하세요");
        return;
    }
       if(Phonegroup.value === null|| Phonegroup.value.trim.toString() ==="")
    {
        alert("그룹을 입력하세요");
        return;
    }
     const body={
         Name:Phoneowner.value.trim ,
         Number:Phonenumber.value.trim,
         Group:Phonegroup.value.trim 
    };
    const re = await fetch('/api/onedelete',{
        method: 'DELETE',
        headers :{'Contents - Type': "application/json"},
        body: JSON.stringify(body)
    });
    if(re.ok)
    {
        alert("삭제 완료");
    }
    else
    {
     throw new Error(await re.text());
    }
}
catch(exception)
{
    console.log(exception);
}
});



function SettingtheTable (data,index)
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
        if(index = 5)
        {
             td.textContent= row[col] === 1?'수신':'발신';
        }
        else
        {
            td.textContent=  row[col];
        }
        tr.appendChild(td);
       });
       tableBody.appendChild(tr);
     })
}
