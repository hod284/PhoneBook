const Phoneowner = document.getElementById('PhoneOwner')as HTMLInputElement;
const Phonenumber = document.getElementById('PhoneNumber')as HTMLInputElement;
const Phonegroup = document.getElementById('Group')as HTMLInputElement;
const Searchingdate = document.getElementById('SearchingDate')as HTMLInputElement;
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
    const re = await fetch('/api/Column_Owner');
    
    if(re.ok)
    {
       const data = await re.json();  
        console.log(data);
        SettingtheTable (data);
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
    const re = await fetch('/api/Column_Number');
    
    if(re.ok)
    {
       const data = await re.json();  
        console.log(data);
        SettingtheTable (data);
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
    const re = await fetch('/api/Column_PhoneGroup');
    
    if(re.ok)
    {
        const data = await re.json();  
        console.log(data);
        SettingtheTable (data);
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
    const re = await fetch('/api/Column_datetime');
    
    if(re.ok)
    {
        const data = await re.json();  
        console.log(data);
        SettingtheTable (data);
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
    const re = await fetch('/api/Column_callingorgetting');
    
    if(re.ok)
    {
        const data = await re.json();  
        console.log(data);
        SettingtheTable (data);
    }
    else
    {
        alert('컬럼이 비었습니다');
    }
}
catch(exception)
{
    console.log(exception);
           alert('컬럼이 비었습니다');
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
       alert('실패했습니다');
}
});
Onedelete.addEventListener("click",async ()=>{
    try{
     if(Phoneowner.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("이름을 입력하세요");
        return;
    }
       if(Phonenumber.value === null|| Phonenumber.value.trim.toString() ==="")
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
getphoneinfobyowner1.addEventListener("click",async ()=>{
try{

  if(Phoneowner.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("이름을 입력하세요");
        return;
    }
    const re = await fetch(`/api/ow/${Phoneowner.value}`);
    if(re.ok)
    {
        const data = await re.json();  
        console.log(data);
        SettingtheTable (data);
    }
    else
    {
       throw new Error(await re.text());
    }
}
catch(exception)
{
    console.log(exception);
    alert("부모에서 받은 데이터 없음");
}
}); 
getphoneinfobyowner2.addEventListener("click",async()=>{
try{
  if(Phoneowner.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("이름을 입력하세요");
        return;
    }
    const re = await fetch(`/api/how/${Phoneowner.value}`);
     if(re.ok)
    {
    const data = await re.json();  
    console.log(data);
    SettingtheTable(data);
    }
      else
    {
       throw new Error(await re.text());
    }
}
catch(exception)
{
    console.log(exception);
        alert("자식에서 받은 데이터 없음");
}
});
getphoneinfobygroup.addEventListener("click",async()=>{
try{

  if(Phonegroup.value === null|| Phonegroup.value ===null)
    {
        alert("그룹을 입력하세요");
        return;
    }
    const re = await fetch(`/api/gr/${Phonegroup.value}`);
      if(re.ok)
    {
    const data = await re.json();  
    console.log(data);
    SettingtheTable(data);
      }
      else
    {
       throw new Error(await re.text());
    }
}
catch(exception)
{
    console.log(exception);
        alert(" 데이터 없음");
}
}); 
getphoneinfobydate.addEventListener('click',async ()=>{
 try
    {
   if(Searchingdate.value === null|| Searchingdate.value.trim.toString() ==="")
    {
        alert("검색할 연도나 달을 입력하세요");
        return;
    }
     const re = await fetch(`/api/da/${Searchingdate.value.trim}`);
     if(re.ok)
    {
         const data = await re.json();  
         console.log(data);
         SettingtheTable(data);
    }
    else
    {
       throw new Error(await re.text());
    }
    }
    catch(exception)
    {
      console.log(exception);
        alert(" 데이터 없음");
    }
});
getPhoneinfobynumber.addEventListener('click',async()=>{
try
{
 if(Phonenumber.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("번호를 입력하세요");
        return;
    }

 const re = await fetch(`/api/nu/${Phoneowner.value.trim}`);
   if(re.ok)
    {
    const data = await re.json();  
    console.log(data);
    SettingtheTable(data);
    }
     else
    {
       throw new Error(await re.text());
    }

}
 catch(exception)
    {
      console.log(exception);
        alert(" 데이터 없음");
    }
}); 


Modiftyphonebook.addEventListener('click',async()=>{
try
{
 if(Phoneowner.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("주인를 입력하세요");
        return;
    }
    if(Phonenumber.value === null|| Phonenumber.value.trim.toString() ==="")
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
  const re = await fetch('/api/changephonbookinfo',{
        method: 'PATCH',
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
        alert(" 데이터 없음");
    }
}); 



Addphonebook.addEventListener('click',async()=>{
try
{
  if(Phoneowner.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("주인를 입력하세요");
        return;
    }
    if(Phonenumber.value === null|| Phonenumber.value.trim.toString() ==="")
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
  const re = await fetch('/api/AddthePhonNumber',{
        method: 'POST',
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
        alert(" 데이터 없음");
    }
}); 


Addphonehistory.addEventListener('click',async()=>{
try
{
 if(Phoneowner.value === null|| Phoneowner.value.trim.toString() ==="")
    {
        alert("오너를 입력하세요");
        return;
    }
    if(Callingorgetting.value === null|| Callingorgetting.value.trim.toString() ==="")
    {
        alert("그룹을 입력하세요");
        return;
    }
 const body={
         CallingName:Phoneowner.value.trim ,
         CallingorGetting:Phonenumber.value.trim
    };
  const re = await fetch('/api/called',{
        method: 'POST',
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
        alert(" 데이터 없음");
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
        if(col === 'phone_callingorgetting')
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
