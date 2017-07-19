var nextPrevEvent=(function(){
  return {
    movePre: function(len,size,cnt,$list) {
      if (cnt > 1) {
        $list.animate({
          'left':  -((cnt-2) * size) + 'px'
        },'fast');
        cnt--;
      }
      return cnt;
    },

    moveNxt: function(len,size,cnt,$list) {
      cnt=cnt%len;
      $list.animate({
        'left': -(cnt * size) + 'px'
      },'fast');
      cnt++;
      return cnt;
    }
  };
})();
