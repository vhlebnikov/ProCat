package com.example.procatfirst.intents

import android.content.Intent

/**
 * Тут представлены два варианта перегруженной функции sendIntent,
 * которая создаёт и отправляет сигнал.
 *
 * Первый вариант - сигнал с дополнительной информацией
 * @param intentToSend - имя сигнала. Имена объявлены в объекте SystemNotifications.
 * @param extraName - имя доп информации. Имена бъявлены в объекте SystemNotificationsExtras.
 * @param extraValue - строка, содержащая произвольную доп информацию, которую хотим передать.
 */
fun NotificationCoordinator.sendIntent(intentToSend: String, extraName: String, extraValue: String) {
    // Create Notification
    val intent = Intent(intentToSend)
    // Add data (Extras)
    intent.putExtra(
        extraName,
        extraValue
    )

    realiseIntent(intent)
}

/**
 * Второй вариант простой, без доп информации.
 * @param intentToSend - В параметрах только имя сигнала в формате SystemNotifications.<SIGNAL_NAME>
 */
fun NotificationCoordinator.sendIntent(intentToSend: String) {
    // Create Notification
    val intent = Intent(intentToSend)

    realiseIntent(intent)
}

/**
 * Собственно отправка сигнала
 * Вынес в отдельный метод для переиспользования кода.
 * !!! Внимание на setPackage. Я долго не мог понять, почему на Дашиной сборке не работает.
 *      Оказалось, что забыл переименовать procattemplate на procatfirst.
 */
private fun realiseIntent(intent: Intent) {
    // Set the package
    intent.setPackage("com.example.procatfirst")
    // Send Notification - This is found in the declaration file
    NotificationCoordinator.shared.sendNotification(intent)
}