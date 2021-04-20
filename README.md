# hrpj Project

---
## Project Setting
 1. STS Setting(lombok 설치 필요)
     - lombok.jar 다운 받은 후 java -jar lombok-1.16.10.jar 실행 후 STS실행파일 선택하여 설치
 2. STS 기본 세팅
     - formatter폴더에 있는 java_formatter_20190930.xml파일 Formatter에 import
     - formatter폴더에 있는 java_codetemplateds_20190930.xml파일 Code Templates에 import
     - formatter폴더에 있는 sts_saveaction_20190930.png 이미지를 보고 Save Actions 세팅
 3. hrpj Project Clone(https://kindhn@bitbucket.org/hrpj/hrpj.git)
 4. Maven Project import 후 Maven Update
 5. Boot Dashboard에 com.hrpj.oauth, com.hrpj.restful Profile값을 local로 설정
 6. **인증이 oauth기반으로 oauth를 먼저 올리고 restful을 올려야 서버가 올라감**
---

## Core Annotation
 - CustomValid, CustomValidParam
     - @CustomValid(validField={필수로 입력받아야 할 map key값(Type: String[])}) 
     - @CustomValidParam (Controller에 넘어오는 parameter값에 Annotation추가)
     - Validation에 실패할경우 **CustomValidException**발생(error code: 0400발생 및 필수 필드 리턴됨)
	 
	     ```
         @CustomValid(validField = { "compnm", "repnm", "domain", "zipcd", "addr1", "addr2", "phone", "fax", "useyn" })
         public Map<String, Object> insertComp( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {
	     ```