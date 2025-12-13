#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

case "$1" in
  start)
    echo "Starting aesdsocket"
    # avoid starting multiple copies
    if pidof aesdsocket >/dev/null 2>&1; then
      echo "aesdsocket already running"
      exit 0
    fi
    if [ -x /usr/bin/aesdsocket ]; then
      /usr/bin/aesdsocket &
      sleep 1
      exit 0
    else
      echo "aesdsocket binary not found"
      exit 1
    fi
    ;;
  stop)
    echo "Stopping aesdsocket"
    killall aesdsocket 2>/dev/null || true
    ;;
  restart)
    $0 stop
    sleep 1
    $0 start
    ;;
  *)
    echo "Usage: $0 {start|stop|restart}"
    exit 2
    ;;
esac

exit 0

