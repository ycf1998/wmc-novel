/**
 * 弹出框
 * YCF
 */
class Message {
	msgTemplate = `<div id="my-message" class="alert with-icon hidden ">
		  <i class="icon-ok-sign"></i>
		  <div class="content"><h4></h4><p></p></div>
		</div>`
	/**
	 * type: 类型
	 * title：标题
	 * message：内容
	 * time：显示时间
	 */
	alert(type = 0, title = '', content = '', time = '3000') {
		document.body.insertAdjacentHTML('beforeEnd', this.msgTemplate);
		let msgBox = document.querySelector('#my-message');
		msgBox.className += this.getClass(type);
		if (title != '') {
			msgBox.querySelector('.content h4').innerHTML = title;
		}
		msgBox.querySelector('.content p').innerHTML = content;
		msgBox.querySelector('i').className = this.getIcon(type);
		msgBox.className = msgBox.className.replace('hidden', '');
		setTimeout(() => msgBox.remove(), time);
	}
	getClass(type) {
		switch(type) {
			case 1: return 'alert-primary';break;
			case 2: return 'alert-success';break;
			case 3: return 'alert-info';break;
			case 4: return 'alert-warning';break;
			case 5: return 'alert-danger';break;
			default: return '';
		}
	}
	getIcon(type) {
		switch(type) {
			case 1: return 'icon-inbox';break;
			case 2: return 'icon-ok-sign';break;
			case 3: return 'icon-info-sign';break;
			case 4: return 'icon-frown';break;
			case 5: return 'icon-remove-sign';break;
			default: return '';
		}
	}
}

const $Message = new Message();