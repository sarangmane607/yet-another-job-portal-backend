# Demo Application for Telstra
I call it "Yet Another Job Portal" :)

### Access this application **[here](https://yet-another-job-portal.web.app/)**

<br/>

There are two repositories for this project<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. React front end ui <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://github.com/sarangmane607/yet-another-job-portal-ui<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. Backend written in java, spring framework<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://github.com/sarangmane607/yet-another-job-portal-backend<br/>
    
Currently **backend (java)** is deployed on **[heroku](https://www.heroku.com/)** cloud and **front-end ui (react)** is deployed on **[firebase](https://firebase.google.com/)** server
<br/>
<br/>
<br/>

### Steps to start react on local machine
*Note : nodejs installation is required on machine*

1. check-out **[this](https://github.com/sarangmane607/yet-another-job-portal-ui)** repo

2. run below command inside repo folder

```
npm run start:dev
```
***If your backend is not running on 8080 port then modify ```REACT_APP_API_BASE_URL``` parameter from  ```.env.development``` file***
<br/>
<br/>
***React ui will start on 3000 port(usually). if not change ```REACT_APP_REACT_URI``` parameter from  ```.env.development``` file***
<br/>
<br/>
<br/>


### Steps to start backend(java) on local machine
1. check-out **[this](https://github.com/sarangmane607/yet-another-job-portal-backend)** repo
2. modify below parameter in ```src\main\resources\application.yml``` file to point it to your react app <br/>
i.e ```http://localhost:3000/oauth2/redirect```
```
    ...
    authorizedRedirectUris:
      - https://yet-another-job-portal.web.app/oauth2/redirect
    ...
```

 
