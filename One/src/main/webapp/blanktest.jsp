<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>no space</title>
    <script>
        // 공백 사용 못 하게
        function noSpaceForm(obj) 
        {             
            var str_space = /\s/;               // 공백 체크
            if(str_space.exec(obj.value)) 
            {     // 공백 체크
                alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백 제거됩니다.");
                obj.focus();
                obj.value = obj.value.replace(' ',''); // 공백제거
                return false;
            }
        }
        
        // 첫 글자 공백만 사용 못 하게
        function noSpaceForm2(obj) 
        {                        
            if(obj.value == " ") // 공백 체크
            {              
                alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백 제거됩니다.");
                obj.focus();
                obj.value = obj.value.replace(' ','');  // 공백 제거
                return false;
            }
        }
    </script>
 
</head>
<body>
    공백 사용 금지 :   <input type="text" name="test" id="test" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />
    <br><br>
    첫 글자 공백만 금지 :   <input type="text" name="test2" id="test2" onkeyup="noSpaceForm2(this);" onchange="noSpaceForm2(this);" />
</body>
</html>


출처: https://0yumin.tistory.com/24 [공유민 블로그]