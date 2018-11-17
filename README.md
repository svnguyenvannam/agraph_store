<strong><h1>Allegro Graph System Store</h1></strong><br>

<strong><h3>Entity</h3></strong>
Package lưu các lớp thực thể, gồm các lớp thực thể Person, Location, Event, Organization, Time, Country, Entity. 
Các lớp đều được kế thừa từ lớp Entity. Đối với mỗi lớp thực thể sẽ có các phương thức get/set. Một phương thức 
để store dữ liệu vào database. Một phương thức tạo ngẫu nhiên dữ liệu tùy vào thuộc tính của class<br>
<br>
<strong><h3>Connection</h3></strong>
Package connection chứa các lớp để giao tiếp với database.<br>
&nbsp;&nbsp;&nbsp;&nbsp;DatabaseConection : Lớp dùng để kết nối với database<br>
&nbsp;&nbsp;&nbsp;&nbsp;Datastoreder : Lớp dùng để lưu dữ liệu<br>
&nbsp;&nbsp;&nbsp;&nbsp;Query : Lớp chứa 20 truy vấn mà đề bài yêu cầu<br> 
&nbsp;&nbsp;&nbsp;&nbsp;Setting : Lớp cài đặt các biến hằng số như userName, pass<br>
<br>
<strong><h3>CreateData</h3></strong>
Package createdata dùng để sinh dữ liệu ngẫu nhiên, tùy với tham số truyền vào mà sẽ tạo ra các bộ dữ liệu gồm 
100, 1000 ... 17000000 triple.<br>
&nbsp;&nbsp;&nbsp;&nbsp;RawDataReader : Lớp dùng để đọc dữ liệu từ text. Lớp có 3 thuộc tính là 3 mảng 2 chiều.
Các mảng lần lượt lưu toàn bộ tên thực thể, toàn bộ mô tả của các thực thể và toàn bộ liên hệ giữa các thực thể.<br>
&nbsp;&nbsp;&nbsp;&nbsp;DataCreator : Tạo ngẫu nhiên các bộ dữ liệu, tạo ngẫu nhiên quan hê giữa 2 thực thể<br>
<br>
<strong><h3>Struc</h3></strong>
Chứa các cấu trúc dữ liệu đơn giản để tiết kiệm thời gian cho truy vấn