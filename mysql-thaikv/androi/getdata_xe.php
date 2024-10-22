<?php
$connect = mysqli_connect("localhost", "root", "", "qlxe");
if (!$connect) {
    die("Kết nối không thành công: " . mysqli_connect_error());
}
mysqli_query($connect, "SET NAMES 'utf8'");

class Xe {
    function __construct($tenxe, $hangsx, $namsx, $hinh) {
        $this->tenxe = $tenxe;
        $this->hangsx = $hangsx;
        $this->namsx = $namsx;
        $this->hinh = $hinh;
    }
}

$query = "SELECT * FROM dsxe";
$mangxe = array();
$data = mysqli_query($connect, $query);

while ($row = mysqli_fetch_assoc($data)) {
    $mangxe[] = $row;
}

echo json_encode($mangxe);
?>
