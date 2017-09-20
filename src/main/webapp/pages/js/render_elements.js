/**
 * 
 */
var templateDefault;




function generatePageElements(employeeCount, pageLimit,listElement) {
	var pagesCount;
	var offsetIncrement =4;
	var offsetCurrent=0;
	var additionalPageCount;
		pagesCount = employeeCount / pageLimit;
	var onClickFunction;	

	for(i=1;i<=pagesCount;i++){
		
		onClickFunction = 'onclick="getSpecificEmployeePage('+i+')"';
		listElement.append('<li><button type="button" id="page_btn_' + i+ '" class="btn btn-default" ' + onClickFunction+ '>'+i +'</button></li>');
		offsetCurrent +=offsetIncrement;
		
	};
	if (employeeCount % pageLimit > 0) {
		additionalPageCount = employeeCount % pageLimit;
		onClickFunction = 'onclick="getSpecificEmployeePage('+i+')"';
		listItem='<li><button id="page_btn_' + i+ '" class="btn btn-default" type="button" ' + onClickFunction+ '>'+i +'</button></li>';
		
		listElement.append(listItem);
	}
};
function renderEmployeeRecord(employee,containerElement){
	var imported = document.createElement('script');
	imported.src = 'js/employeeTemplate.js';
	document.head.appendChild(imported);
	var id = employee.id;
	var firstName = employee.employeeFirstName;
	var lastName = employee.employeeLastName;
	var age = employee.age;
	var lengthOfService = employee.lengthOfService;
	var empl = new Employee(id, firstName, lastName, age,
			lengthOfService);
	var employeeTemplate = $('#employees-template').html();
	if(employeeTemplate == undefined){
		employeeTemplate =templateDefault;
	}
	containerElement.append(Mustache.render(
			employeeTemplate, empl));
}