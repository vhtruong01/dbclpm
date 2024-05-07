// Các quận và phường tương ứng
var districtWards = {
    'Hà Đông': [
        'Biên Giang', 'Đồng Mai', 'Dương Nội', 'Hà Cầu', 'Kiến Hưng', 'La Khê', 'Mộ Lao',
        'Nguyễn Trãi', 'Phú La', 'Phú Lãm', 'Phú Lương', 'Phúc La', 'Quang Trung', 'Vạn Phúc',
        'Văn Quán', 'Yên Nghĩa', 'Yết Kiêu'
    ],
    'Đống Đa': [
        'Cát Linh', 'Hàng Bột', 'Khâm Thiên', 'Khương Thượng', 'Kim Liên', 'Láng Hạ',
        'Láng Thượng', 'Nam Đồng', 'Ngã Tư Sở', 'Phương Liên', 'Phương Mai', 'Quang Trung',
        'Quốc Tử Giám', 'Thịnh Quang', 'Thổ Quan', 'Trung Liệt', 'Trung Phụng', 'Trung Tự',
        'Văn Chương', 'Văn Miếu', 'Ô Chợ Dừa'
    ],
    'Thanh Xuân': [
        'Hạ Đình', 'Khương Đình', 'Khương Mai', 'Khương Trung', 'Kim Giang', 'Nhân Chính',
        'Phương Liệt', 'Thanh Xuân Bắc', 'Thanh Xuân Nam', 'Thanh Xuân Trung', 'Thượng Đình'
    ],


    // Các quận và phường khác...
};

// Lấy các phường tương ứng với quận đã chọn
// Lấy các phường tương ứng với quận đã chọn
function getWardsByDistrict() {
  var districtSelect = document.getElementById('district');
  var wardSelect = document.getElementById('ward');
  var selectedDistrict = districtSelect.value;

  // Xóa danh sách phường hiện tại
  wardSelect.innerHTML = '';

  if (selectedDistrict) {
    // Bỏ trạng thái disabled của ô chọn phường
    wardSelect.disabled = false;
    wardSelect.classList.remove('bg-gray-200');

    var wards = districtWards[selectedDistrict];
    for (var i = 0; i < wards.length; i++) {
      var option = document.createElement('option');
      option.value = wards[i];
      option.textContent = wards[i];

      // Kiểm tra xem phường có giá trị được chọn trong dữ liệu người dùng hay không
      if (option.value === '${user.xaPhuong}') {
        option.selected = true;
      }

      wardSelect.appendChild(option);
    }
  } else {
    // Nếu không có quận nào được chọn, vô hiệu hóa ô chọn phường
    wardSelect.disabled = true;
    wardSelect.classList.add('bg-gray-200');
    var option = document.createElement('option');
    option.textContent = '-- Chọn Quận trước --';
    wardSelect.appendChild(option);
  }
}

// Lắng nghe sự kiện khi chọn quận
document.getElementById('district').addEventListener('change', function () {
    getWardsByDistrict();
});



// validate sdt
var phoneInput = document.getElementById('sdt');
var phoneError = document.getElementById('sdt-error');

phoneInput.addEventListener('input', function () {
    var phone = phoneInput.value;

    if (!validatePhoneNumber(phone)) {
        phoneError.textContent = 'Số điện thoại không hợp lệ.';
    } else {
        phoneError.textContent = '';
    }
});

function validatePhoneNumber(phone) {
    // Kiểm tra điều kiện của số điện thoại, ví dụ:
    var phoneRegex = /^\d{10}$/;
    return phoneRegex.test(phone);
}

// validate mat khau
var passwordInput = document.getElementById('password');
var passwordError = document.getElementById('password-error');

passwordInput.addEventListener('input', validatePassword);

function validatePassword() {
    var password = passwordInput.value;

    // Kiểm tra điều kiện mật khẩu mạnh
    var strongPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()\-+=_<>,./?;:'"\\|[\]{}~`])[A-Za-z\d!@#$%^&*()\-+=_<>,./?;:'"\\|[\]{}~`]{8,}$/;

    if (!strongPasswordRegex.test(password)) {
        passwordError.textContent = 'Mật khẩu yếu. Nhập mật khẩu ít nhất có 8 kí tự có chứa cả chữ thường, chữ hoa, số và ký tự đặc biệt.';
    } else {
        passwordError.textContent = '';
    }
}

// validate 2 mat khau bang nhau
var passwordInput1 = document.getElementById('password');
var confirmPasswordInput = document.getElementById('repeatpassword');
var passwordError1 = document.getElementById('repeatpassword-error');

passwordInput1.addEventListener('input', validatePassword1);
confirmPasswordInput.addEventListener('input', validatePassword1);

function validatePassword1() {
    var password = passwordInput1.value;
    var confirmPassword = confirmPasswordInput.value;

    if (password !== confirmPassword) {
        passwordError1.textContent = 'Mật khẩu nhập lại không đúng!';
    } else {
        passwordError1.textContent = '';
    }
}